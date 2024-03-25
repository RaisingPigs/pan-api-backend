package com.pan.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-23 15:35
 **/
@Data
@Component
@ConfigurationProperties(prefix = "login.gitee")
public class GiteeLoginConfig {
    private String clientId;
    private String clientSecret;
    private String state;
    private String usernamePrefix;
    private String callbackUrl;
    private String loginUrl;
    private String tokenUrl;
    private String userUrl;
}
