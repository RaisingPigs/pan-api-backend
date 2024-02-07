package com.pan.gateway.config;

import com.pan.common.constant.GatewayConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
public class FeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(GatewayConstant.REQ_SOURCE_KEY, GatewayConstant.REQ_SOURCE_VALUE);
        };
    }
}