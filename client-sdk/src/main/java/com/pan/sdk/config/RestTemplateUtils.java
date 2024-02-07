package com.pan.sdk.config;

import com.pan.sdk.interceptor.HeaderRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-22 14:12
 **/
public class RestTemplateUtils {
    public static RestTemplate getRestTemplate() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(interceptors);

        changeMessageConverter2UTF8(restTemplate);
            
        return restTemplate;
    }

    private static void changeMessageConverter2UTF8(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if(httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(StandardCharsets.UTF_8);
                break;
            }
        }
    }
}
