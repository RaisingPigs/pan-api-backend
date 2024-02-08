package com.pan.sdk;

import com.pan.sdk.client.ItfClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-24 14:53
 **/
@Configuration
@ComponentScan
@ConfigurationProperties("pan-api.client")
public class ApiClientConfig {
    private String accessKey;
    private String secretKey;

    public ApiClientConfig() {
    }

    @Bean
    public ItfClient itfClient() {
        return new ItfClient(accessKey, secretKey);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
