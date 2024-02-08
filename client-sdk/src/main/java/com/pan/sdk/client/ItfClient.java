package com.pan.sdk.client;

import com.pan.sdk.config.RestTemplateUtils;
import com.pan.sdk.util.SignUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-22 13:59
 **/
public class ItfClient {
    private final RestTemplate restTemplate;

    private String accessKey;
    private String secretKey;

    public ItfClient() {
        this.restTemplate = RestTemplateUtils.getRestTemplate();
    }

    public ItfClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.restTemplate = RestTemplateUtils.getRestTemplate();
    }

    public String doGet(String url, Map<String, ?> queryParams) {
        return doHttp(url, HttpMethod.GET, queryParams, null);
    }

    public String doPost(String url, Map<String, ?> queryParams, Map<String, ?> bodyParam) {
        return doHttp(url, HttpMethod.POST, queryParams, bodyParam);
    }

    private String doHttp(String url, HttpMethod method, Map<String, ?> queryParams, Map<String, ?> bodyParam) {
        String urlWithParams = expandQueryParams(url, queryParams);
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<?> httpEntity;

        if (method == HttpMethod.GET) {
            httpEntity = new HttpEntity<>(headers);
        } else if (method == HttpMethod.POST) {
            httpEntity = new HttpEntity<>(bodyParam, headers);
        } else {
            throw new RuntimeException("只支持post或者get方法");
        }

        ResponseEntity<String> exchange = restTemplate.exchange(urlWithParams, method, httpEntity, String.class);
        return exchange.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();

        /*请求头中添加 ak, nonce, timestamp, sign*/
        headers.setAll(SignUtils.getHeaderMap(accessKey, secretKey));

        return headers;
    }

    private String expandQueryParams(String url, Map<String, ?> queryParams) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
            .fromHttpUrl(url);

        if (!CollectionUtils.isEmpty(queryParams)) {
            for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
                uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        UriComponents uriComponents = uriComponentsBuilder.build();
        return uriComponents.toUriString();
    }
}


