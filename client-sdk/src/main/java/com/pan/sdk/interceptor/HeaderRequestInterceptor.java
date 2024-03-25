package com.pan.sdk.interceptor;

import com.pan.sdk.constant.HeaderConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Collections;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-01-18 11:55
 **/
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();

        /*使用json传递数据, 请求头必须设置json*/
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.put(HeaderConstant.CLIENT_KEY, Collections.singletonList(HeaderConstant.CLIENT_VAL));

        return execution.execute(request, body);
    }
}
