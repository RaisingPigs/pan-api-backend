package com.pan.gateway.config;

import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Optional;

@Configuration
public class KeyResolverConfig {
    @Bean("ipKeyResolver")
    public KeyResolver keyResolver() {
        return exchange -> {
            String ip = Optional.ofNullable(exchange.getRequest().getRemoteAddress())
                .map(InetSocketAddress::getHostString)
                .orElseThrow(() -> new BusinessException(ResultCode.NO_AUTH, "无法获取ip"));

            return Mono.just(ip);
        };
    }
}