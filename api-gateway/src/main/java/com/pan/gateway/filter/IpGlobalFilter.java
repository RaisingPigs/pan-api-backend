package com.pan.gateway.filter;

import com.pan.model.constant.RedisConstant;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Optional;
import java.util.Set;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-03 22:13
 **/
@Component
@RequiredArgsConstructor
public class IpGlobalFilter implements GlobalFilter, Ordered {
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String host = Optional.ofNullable(request.getRemoteAddress())
            .map(InetSocketAddress::getHostString)
            .orElse("");

        Set<String> ipBlackList = stringRedisTemplate.boundSetOps(RedisConstant.IP_BLACK_LIST).members();

        if (!CollectionUtils.isEmpty(ipBlackList) && ipBlackList.contains(host)) {
            throw new BusinessException(ResultCode.NO_AUTH, "当前ip无法访问");
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -98;
    }
}
