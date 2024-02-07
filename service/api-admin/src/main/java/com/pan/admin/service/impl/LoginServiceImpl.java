package com.pan.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.pan.common.constant.UserConstant;
import com.pan.admin.service.LoginService;
import com.pan.admin.service.UserService;
import com.pan.common.util.AuthUtils;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import com.pan.common.util.AkSkUtils;
import com.pan.model.converter.user.UserDTOConverter;
import com.pan.model.dto.user.UserDTO;
import com.pan.model.entity.User;
import com.pan.model.enums.user.GenderEnum;
import com.pan.model.enums.user.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-05 08:38
 **/
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserService userService;

    @Override
    public long userRegister(String username, String password, String checkPassword) {
        if (StringUtils.isAnyBlank(username, password, checkPassword)) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        if (username.length() < 5
            || username.length() > 18
            || password.length() < 5
            || password.length() > 18) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "用户名密码长度不符合规范");
        }

        if (!checkPassword.equals(password)) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "两次输入密码不一致");
        }

        synchronized (username.intern()) {
            long count = userService.lambdaQuery().eq(User::getUsername, username).count();
            if (count > 0) {
                throw new BusinessException(ResultCode.SAVE_ERR, "该用户名已被使用");
            }


            /*生成ak, sk*/
            String accessKey = AkSkUtils.getAccessKey(username, UserConstant.SALT);
            String secretKey = AkSkUtils.getSecretKey(username, UserConstant.SALT);

            /*加密*/
            String encryptPassword = AuthUtils.encryptPassword(password);
            String defaultName = UserConstant.DEFAULT_USERNAME_PREFIX + RandomUtil.randomString(10);

            User user = new User(
                null,
                defaultName,
                username,
                encryptPassword,
                UserConstant.DEFAULT_AVATAR,
                accessKey,
                secretKey,
                GenderEnum.MALE,
                RoleEnum.USER
            );

            boolean save = userService.save(user);
            if (!save) {
                throw new BusinessException(ResultCode.SAVE_ERR);
            }

            return user.getId();
        }

    }

    @Override
    public String userLogin(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "用户名密码不能为空");
        }
        if (username.length() < 5
            || username.length() > 18
            || password.length() < 5
            || password.length() > 18) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "用户名或密码错误");
        }

        String encryptPassword = AuthUtils.encryptPassword(password);
        User user = userService.lambdaQuery()
            .eq(User::getUsername, username)
            .eq(User::getPassword, encryptPassword)
            .one();
        if (user == null) {
            throw new BusinessException(ResultCode.NULL_ERR, "用户名或密码错误");
        }

        UserDTO userDTO = UserDTOConverter.INSTANCE.toUserDTO(user);
        StpUtil.login(user.getId());
        AuthUtils.setLoginUser(userDTO);

        return StpUtil.getTokenValue();
    }

    @Override
    public void userLogout() {
        StpUtil.logout();

    }
}
