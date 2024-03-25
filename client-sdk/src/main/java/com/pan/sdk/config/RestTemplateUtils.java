package com.pan.sdk.config;

import com.pan.sdk.interceptor.HeaderRequestInterceptor;
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
    private static final RestTemplate REST_TEMPLATE;

    static {
        REST_TEMPLATE = new RestTemplate();
        setInterceptors();
        changeMessageConverter2UTF8();
    }

    private static void changeMessageConverter2UTF8() {
        List<HttpMessageConverter<?>> list = REST_TEMPLATE.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(StandardCharsets.UTF_8);
                break;
            }
        }
    }

    private static void setInterceptors() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor());
        REST_TEMPLATE.setInterceptors(interceptors);
    }

    public static RestTemplate getRestTemplate() {
        return REST_TEMPLATE;
    }


}
