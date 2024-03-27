package com.pan.admin.service.impl;

import com.pan.admin.handler.GiteeLoginHandler;
import com.pan.admin.service.LoginBy3rd;
import com.pan.admin.service.UserService;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import org.springframework.stereotype.Component;
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
    private GiteeLoginHandler giteeLoginHandler;

    public LoginBy3rdAdapter(UserService userService) {
        super(userService);
    }

    @Override
    public String userLoginByGitee(String code) {
        String giteeName = giteeLoginHandler.getUserByGitee(code);

        String username = giteeLoginHandler.getUsernamePrefix() + giteeName;
        String password = username;

        return autoRegisterAndLoginBy3rd(username, password);
    }

    @Override
    public String userLoginByQQ(String state, String code) {
        throw new BusinessException(ResultCode.SYSTEM_ERR, "功能正在开发中");
    }

    private String autoRegisterAndLoginBy3rd(String username, String password) {
        if (checkUserExists(username)) {
            /*如果第三方账户的用户已经存在, 则直接登录*/
            return super.userLogin(username, password);
        }

        super.userRegister(username, password, password);

        return super.userLogin(username, password);
    }
}
