package com.pan.admin.service.impl;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.pan.admin.config.GiteeLoginConfig;
import com.pan.admin.service.LoginBy3rd;
import com.pan.admin.service.UserService;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-22 16:00
 **/
@Component
public class LoginBy3rdAdapter extends LoginServiceImpl implements LoginBy3rd {
    @Resource
    private GiteeLoginConfig giteeLoginConfig;
    @Resource
    private RestTemplate restTemplate;

    public LoginBy3rdAdapter(UserService userService) {
        super(userService);
    }

    @Override
    public String userLoginByGitee(String state, String code) {
        String giteeToken = getTokenFromGitee(code);
        String giteeName = getNameFromGitee(giteeToken);

        String username = giteeLoginConfig.getUsernamePrefix() + giteeName;
        String password = username;
        return autoRegisterAndLoginBy3rd(username, password);
    }

    private String getTokenFromGitee(String code) {
        JsonNode tokenResp = restTemplate.postForObject(
            giteeLoginConfig.getTokenUrl(),
            new HttpEntity<>(MapUtil.of("client_secret", giteeLoginConfig.getClientSecret())),
            JsonNode.class,
            MapUtil.of("code", code)
        );

        Assert.notNull(tokenResp, "tokenResp为null");

        return tokenResp.get("access_token").asText();
    }

    private String getNameFromGitee(String token) {
        JsonNode userResp = restTemplate.getForObject(
            giteeLoginConfig.getUserUrl(), 
            JsonNode.class, 
            MapUtil.of("token", token)
        );

        Assert.notNull(userResp, "userResp为null");

        return userResp.get("name").asText();
    }

    private String autoRegisterAndLoginBy3rd(String username, String password) {
        if (checkUserExists(username)) {
            /*如果第三方账户的用户已经存在, 则直接登录*/
            return super.userLogin(username, password);
        }

        super.userRegister(username, password, password);

        return super.userLogin(username, password);
    }

    @Override
    public String userLoginByQQ(String state, String code) {
        throw new BusinessException(ResultCode.SYSTEM_ERR, "功能正在开发中");
    }
}
