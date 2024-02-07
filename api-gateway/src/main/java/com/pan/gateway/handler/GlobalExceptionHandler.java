package com.pan.gateway.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.common.resp.ResultUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Order(-1)
@Component
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        log.error("系统异常: [{}]", ex.getCause().getMessage(), ex);
        BaseResponse<?> baseResponse;
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            baseResponse = ResultUtils.failed(businessException);
        } else {
            baseResponse = ResultUtils.failed(ResultCode.SYSTEM_ERR, ex.getCause().getMessage());
        }

        BaseResponse<?> finalBaseResponse = baseResponse;
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(finalBaseResponse));
            } catch (JsonProcessingException e) {
                log.warn("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}