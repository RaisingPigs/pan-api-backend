package com.pan.admin.handler;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-25 19:03
 **/
@Data
@Component
@ConfigurationProperties(prefix = "login.gitee")
@RequiredArgsConstructor
public class GiteeLoginHandler {
    private final RestTemplate restTemplate;
    private String clientId;
    private String clientSecret;
    private String state;
    private String usernamePrefix;
    private String callbackUrl;
    private String loginUrl;
    private String tokenUrl;
    private String userUrl;

    public String getUserByGitee(String code) {
        String giteeToken = getTokenFromGitee(code);
        return getNameFromGitee(giteeToken);
    }

    private String getTokenFromGitee(String code) {
        JsonNode tokenResp = restTemplate.postForObject(
            getTokenUrl(),
            new HttpEntity<>(MapUtil.of("client_secret", getClientSecret())),
            JsonNode.class,
            MapUtil.of("code", code)
        );

        Assert.notNull(tokenResp, "tokenResp为null");

        return tokenResp.get("access_token").asText();
    }

    private String getNameFromGitee(String token) {
        JsonNode userResp = restTemplate.getForObject(
            getUserUrl(),
            JsonNode.class,
            MapUtil.of("token", token)
        );

        Assert.notNull(userResp, "userResp为null");

        return userResp.get("name").asText();
    }
}
