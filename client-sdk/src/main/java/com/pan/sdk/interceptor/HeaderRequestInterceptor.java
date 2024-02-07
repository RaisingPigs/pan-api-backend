package com.pan.sdk.interceptor;

import com.pan.sdk.constant.HeaderConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


    private List<String> getCookies() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null) {
            return null;
        }

        HttpServletRequest request = requestAttributes.getRequest();
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies) || cookies.length == 0) {
            return null;
        }
        return Arrays.stream(cookies)
            .map(cookie -> cookie.getName() + "=" + cookie.getValue())
            .collect(Collectors.toList());
    }
}
