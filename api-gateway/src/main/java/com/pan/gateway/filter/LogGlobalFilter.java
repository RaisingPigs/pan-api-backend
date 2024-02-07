package com.pan.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-03 22:08
 **/
@Slf4j
@Component
public class LogGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /*计时*/
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ServerHttpRequest request = exchange.getRequest();

        String id = request.getId();
        String path = request.getPath().toString();
        String method = Optional.ofNullable(request.getMethod())
            .map(HttpMethod::toString)
            .orElse("method未知");
        String queryParams = request.getQueryParams().toString();
        String address = Optional.ofNullable(request.getRemoteAddress())
            .map(InetSocketAddress::getHostString)
            .orElse("ip未知");

        log.info("请求id: [{}], 路径: [{}], 方式: [{}], 参数: [{}], ip: [{}]", id, path, method, queryParams, address);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            stopWatch.stop();
            long timeMillis = stopWatch.getTotalTimeMillis();
            log.info("request end. id: [{}], cost: [{}]ms", id, timeMillis);
        }));
    }

    @Override
    public int getOrder() {
        return -99;
    }
}
