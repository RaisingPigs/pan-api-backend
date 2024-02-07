// package com.pan.gateway.backup;
//
// import cn.hutool.core.util.StrUtil;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.pan.common.exception.BusinessException;
// import com.pan.common.resp.BaseResponse;
// import com.pan.common.resp.ResultCode;
// import com.pan.gateway.backup.SignCheckTest;
// import com.pan.gateway.backup.BaseResponseUtils;
// import com.pan.model.entity.InterfaceInfo;
// import com.pan.model.entity.User;
// import com.pan.model.req.userinterfaceinfo.InvokeAuthCheckReq;
// import com.pan.model.req.userinterfaceinfo.InvokeCountReq;
// import com.pan.sdk.constant.SignConstant;
// import lombok.RequiredArgsConstructor;
// import lombok.SneakyThrows;
// import lombok.extern.slf4j.Slf4j;
// import org.reactivestreams.Publisher;
// import org.springframework.cloud.gateway.filter.GatewayFilterChain;
// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.core.Ordered;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.core.io.buffer.DataBuffer;
// import org.springframework.core.io.buffer.DataBufferFactory;
// import org.springframework.core.io.buffer.DataBufferUtils;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.server.reactive.ServerHttpRequest;
// import org.springframework.http.server.reactive.ServerHttpResponse;
// import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
// import org.springframework.stereotype.Component;
// import org.springframework.web.reactive.function.client.WebClient;
// import org.springframework.web.server.ServerWebExchange;
// import reactor.core.publisher.Flux;
// import reactor.core.publisher.Mono;
//
// import java.nio.charset.StandardCharsets;
// import java.util.Objects;
// import java.util.Optional;
// import java.util.concurrent.CompletableFuture;
// import java.util.concurrent.ExecutionException;
//
// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class TestFilter implements GlobalFilter, Ordered {
//     private static final String ADMIN_URL = "/api/admin";
//     private final ObjectMapper objectMapper;
//     WebClient webClient = WebClient.builder()
//         .baseUrl("http://localhost:9999/api/admin")
//         .defaultHeader("source", "pai-api-gateway")
//         .build();
//
//     @SneakyThrows
//     @Override
//     public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//         ServerHttpRequest request = exchange.getRequest();
//
//         String path = request.getPath().toString();
//         if (path.startsWith(ADMIN_URL)) {
//             return chain.filter(exchange);
//         }
//
//         HttpHeaders headers = request.getHeaders();
//
//
//         /*签名校验*/
//         Mono<Void> mono = checkSign(headers);
//         return mono.doOnSuccess(valid -> {
//             /*获取用户和接口信息*/
//             Mono<User> userMono = getUserByAccessKey(headers.getFirst(SignConstant.ACCESS_KEY));
//             Mono<InterfaceInfo> interfaceInfoMono = getInterfaceInfo(request);
//             Mono.zip(userMono, interfaceInfoMono)
//                 .doOnSuccess(tuple -> {
//                     User user = tuple.getT1();
//                     InterfaceInfo interfaceInfo = tuple.getT2();
//                     checkInvokeAuth(new InvokeAuthCheckReq(user.getId(), interfaceInfo.getId()))
//                         .then(Mono.defer(() -> {
//                             /*使用response来获取响应体, 然后使接口调用次数+1*/
//                             ServerHttpResponse originalResponse = exchange.getResponse();
//                             DataBufferFactory bufferFactory = originalResponse.bufferFactory();
//                             InvokeCountReq invokeCountReq = new InvokeCountReq(user.getId(), interfaceInfo.getId());
//                             ServerHttpResponseDecorator decoratedResponse = getDecoratedResponse(originalResponse, bufferFactory, invokeCountReq);
//
//                             // replace response with decorator
//                             return chain.filter(exchange.mutate().response(decoratedResponse).build());
//                         }));
//                 });
//         });
//     }
//
//     private Mono<Void> checkSign(HttpHeaders headers) {
//         String accessKey = headers.getFirst(SignConstant.ACCESS_KEY);
//         String nonce = headers.getFirst(SignConstant.NONCE);
//         String timestamp = headers.getFirst(SignConstant.TIMESTAMP);
//         String sign = headers.getFirst(SignConstant.SIGN);
//
//         if (StrUtil.isBlank(timestamp)) {
//             throw new BusinessException(ResultCode.NO_AUTH);
//         }
//
//         SignCheckTest signCheck = new SignCheckTest(accessKey, nonce, Long.parseLong(timestamp), sign);
//         return signCheck.isValid();
//     }
//
//     private Mono<User> getUserByAccessKey(String accessKey) {
//         return webClient.get()
//             .uri("")
//             .retrieve()
//             .bodyToMono(new ParameterizedTypeReference<BaseResponse<User>>() {
//             })
//             .filter(BaseResponseUtils::isRespSuccessful)
//             .map(BaseResponse::getData)
//             .filter(Objects::nonNull);
//     }
//
//     private Mono<InterfaceInfo> getInterfaceInfo(ServerHttpRequest request) {
//         String path = request.getPath().toString();
//         String method = Optional.ofNullable(request.getMethod())
//             .map(HttpMethod::toString)
//             .orElseThrow(() -> new BusinessException(ResultCode.SYSTEM_ERR));
//
//         /*使用Rpc或者feign发送请求, 判断接口是否存在*/
//         return webClient.get()
//             .uri("")
//             .attribute("path", path)
//             .attribute("method", method)
//             .retrieve()
//             .bodyToMono(new ParameterizedTypeReference<BaseResponse<InterfaceInfo>>() {
//             })
//             .filter(BaseResponseUtils::isRespSuccessful)
//             .map(BaseResponse::getData)
//             .filter(Objects::nonNull);
//     }
//
//
//     private Mono<Void> checkInvokeAuth(InvokeAuthCheckReq invokeAuthCheckReq) {
//         return webClient.post()
//             .uri("")
//             .body(invokeAuthCheckReq, InvokeAuthCheckReq.class)
//             .retrieve()
//             .bodyToMono(new ParameterizedTypeReference<BaseResponse<Boolean>>() {
//             })
//             .filter(BaseResponseUtils::isRespSuccessful)
//             .map(BaseResponse::getData)
//             .filter(Objects::nonNull)
//             .filter(b -> b)
//             .then();
//     }
//
//     private ServerHttpResponseDecorator getDecoratedResponse(ServerHttpResponse originalResponse, DataBufferFactory bufferFactory, InvokeCountReq invokeCountReq) {
//         return new ServerHttpResponseDecorator(originalResponse) {
//             @Override
//             public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                 if (originalResponse.getStatusCode() != HttpStatus.OK
//                     || !(body instanceof Flux)) {
//                     return super.writeWith(body);
//                 }
//
//                 Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
//                 return super.writeWith(fluxBody.map(dataBuffer -> {
//                     String respJson = getContent(dataBuffer);
//
//
//                     CompletableFuture<Void> permFuture = CompletableFuture.supplyAsync(() -> {
//                         invokeCountIncrement(respJson, invokeCountReq);
//                         return null;
//                     });
//
//                     try {
//                         permFuture.get();
//                     } catch (InterruptedException | ExecutionException e) {
//                         throw new RuntimeException(e);
//                     }
//
//                     return bufferFactory.wrap(respJson.getBytes(StandardCharsets.UTF_8));
//                 }));
//             }
//
//             @Override
//             public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
//                 return writeWith(Flux.from(body)
//                     .flatMapSequential(p -> p));
//             }
//         };
//     }
//
//
//     private String getContent(DataBuffer dataBuffer) {
//         byte[] content = new byte[dataBuffer.readableByteCount()];
//         dataBuffer.read(content);
//         //释放掉内存
//         DataBufferUtils.release(dataBuffer);
//
//         return new String(content, StandardCharsets.UTF_8);
//     }
//
//     @SneakyThrows
//     private Mono<Boolean> invokeCountIncrement(String respJson, InvokeCountReq invokeCountReq) {
//         BaseResponse<?> baseResponse = objectMapper.readValue(respJson, BaseResponse.class);
//         if (!BaseResponseUtils.isRespSuccessful(baseResponse)) {
//             return Mono.just(false);
//         }
//
//         /*如果接口调用成功, 则调用次数+1
//          * 如果调用次数+1失败, 则抛异常, 让系统显示异常结果, 不给用户看真实的接口返回值*/
//         return webClient.post()
//             .uri("")
//             .body(invokeCountReq, InvokeCountReq.class)
//             .retrieve()
//             .bodyToMono(new ParameterizedTypeReference<BaseResponse<Void>>() {
//             })
//             .map(resp -> {
//                 if (!BaseResponseUtils.isRespSuccessful(resp)) {
//                     throw new BusinessException(ResultCode.SYSTEM_ERR);
//                 }
//                 return true;
//             });
//     }
//
//     @Override
//     public int getOrder() {
//         return -2;
//     }
// }