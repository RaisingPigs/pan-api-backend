package com.pan.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pan.client.ItfFeignClient;
import com.pan.client.UserFeignClient;
import com.pan.client.UserItfFeignClient;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.common.util.BaseRespUtils;
import com.pan.common.util.SpringContextUtils;
import com.pan.gateway.check.SignCheck;
import com.pan.model.entity.Itf;
import com.pan.model.entity.User;
import com.pan.model.req.itf.PathMethodReq;
import com.pan.model.req.user.AccessKeyReq;
import com.pan.model.req.useritf.InvokeAuthCheckReq;
import com.pan.model.req.useritf.InvokeCountReq;
import com.pan.sdk.constant.SignConstant;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItfInvokeGlobalFilter implements GlobalFilter, Ordered {
    private static final String ADMIN_URL = "/api/admin";
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().toString();
        if (path.startsWith(ADMIN_URL)) {
            return chain.filter(exchange);
        }

        HttpHeaders headers = request.getHeaders();

        CompletableFuture<InvokeCountReq> permFuture = CompletableFuture.supplyAsync(() -> {
            /*签名校验*/
            checkSign(headers);

            /*获取用户和接口信息*/
            User user = getUserByAccessKey(headers.getFirst(SignConstant.ACCESS_KEY));
            Itf itf = getItf(request);

            /*校验用户是否有调用该接口的权限*/
            checkInvokeAuth(new InvokeAuthCheckReq(user.getId(), itf.getId()));
            return new InvokeCountReq(user.getId(), itf.getId());
        }, Executors.newCachedThreadPool());
        InvokeCountReq invokeCountReq = permFuture.get();

        /*使用response来获取响应体, 然后使接口调用次数+1*/
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = getDecoratedResponse(originalResponse, bufferFactory, invokeCountReq);
        
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private void checkSign(HttpHeaders headers) {
        String accessKey = headers.getFirst(SignConstant.ACCESS_KEY);
        String nonce = headers.getFirst(SignConstant.NONCE);
        String timestamp = headers.getFirst(SignConstant.TIMESTAMP);
        String sign = headers.getFirst(SignConstant.SIGN);

        if (StrUtil.isBlank(timestamp)) {
            throw new BusinessException(ResultCode.NO_AUTH);
        }

        SignCheck signCheck = new SignCheck(accessKey, nonce, Long.parseLong(timestamp), sign);
        if (!signCheck.isValid()) {
            throw new BusinessException(ResultCode.NO_AUTH);
        }
    }

    private User getUserByAccessKey(String accessKey) {
        UserFeignClient userFeignClient = SpringContextUtils.getBean(UserFeignClient.class);
        BaseResponse<User> baseResponse = userFeignClient.getUserByAccessKey(new AccessKeyReq(accessKey));
        if (BaseRespUtils.isFailedWithoutEmpty(baseResponse)) {
            throw new BusinessException(ResultCode.NO_AUTH);
        }

        return baseResponse.getData();
    }

    private Itf getItf(ServerHttpRequest request) {
        String path = request.getPath().toString();
        String method = Optional.ofNullable(request.getMethod())
            .map(HttpMethod::toString)
            .orElseThrow(() -> new BusinessException(ResultCode.SYSTEM_ERR));

        /*使用Rpc或者feign发送请求, 判断接口是否存在*/
        ItfFeignClient itfFeignClient = SpringContextUtils.getBean(ItfFeignClient.class);
        BaseResponse<Itf> baseResponse = itfFeignClient.getItfByPathAndMethod(new PathMethodReq(path, method));

        if (BaseRespUtils.isFailedWithoutEmpty(baseResponse)) {
            throw new BusinessException(ResultCode.NO_AUTH);
        }

        return baseResponse.getData();
    }

    private void checkInvokeAuth(InvokeAuthCheckReq invokeAuthCheckReq) {
        UserItfFeignClient userItfFeignClient = SpringContextUtils.getBean(UserItfFeignClient.class);
        BaseResponse<Boolean> baseResponse = userItfFeignClient.checkInvokeAuth(invokeAuthCheckReq);

        if (BaseRespUtils.isFailedWithoutEmpty(baseResponse)
            || !baseResponse.getData()) {
            throw new BusinessException(ResultCode.NO_AUTH);
        }
    }

    private ServerHttpResponseDecorator getDecoratedResponse(ServerHttpResponse originalResponse, DataBufferFactory bufferFactory, InvokeCountReq invokeCountReq) {
        return new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (originalResponse.getStatusCode() != HttpStatus.OK
                    || !(body instanceof Flux)) {
                    return super.writeWith(body);
                }

                Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                return super.writeWith(fluxBody.map(dataBuffer -> {
                    String respJson = getContent(dataBuffer);

                    CompletableFuture.supplyAsync(() -> {
                        invokeCountIncrement(respJson, invokeCountReq);
                        return null;
                    }, Executors.newCachedThreadPool());

                    return bufferFactory.wrap(respJson.getBytes(StandardCharsets.UTF_8));
                }));
            }

            @Override
            public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
                return writeWith(Flux.from(body)
                    .flatMapSequential(p -> p));
            }
        };
    }


    private String getContent(DataBuffer dataBuffer) {
        byte[] content = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(content);
        //释放掉内存
        DataBufferUtils.release(dataBuffer);

        return new String(content, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    private void invokeCountIncrement(String respJson, InvokeCountReq invokeCountReq) {
        BaseResponse<?> baseResponse = objectMapper.readValue(respJson, BaseResponse.class);
        if (BaseRespUtils.isFailed(baseResponse)) {
            return;
        }

        /*如果接口调用成功, 则调用次数+1
         * 如果调用次数+1失败, 则抛异常, 让系统显示异常结果, 不给用户看真实的接口返回值*/
        UserItfFeignClient userItfFeignClient = SpringContextUtils.getBean(UserItfFeignClient.class);
        BaseResponse<Void> invokeCountIncrementResponse = userItfFeignClient.invokeCountIncrement(invokeCountReq);
        if (BaseRespUtils.isFailed(invokeCountIncrementResponse)) {
            throw new BusinessException(ResultCode.SYSTEM_ERR);
        }
    }

    @Override
    public int getOrder() {
        return -2;
    }
}