package com.pan.gateway.config;

import com.pan.model.constant.GatewayConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(GatewayConstant.REQ_SOURCE_KEY, GatewayConstant.REQ_SOURCE_VALUE);
    }
}