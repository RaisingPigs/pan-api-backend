package com.pan.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.pan.admin.annotation.LoginLog;
import com.pan.admin.factory.LoginBy3rdUrlFactory;
import com.pan.admin.handler.GiteeLoginHandler;
import com.pan.admin.service.LoginBy3rd;
import com.pan.admin.service.LoginService;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.common.resp.ResultUtils;
import com.pan.common.util.AuthUtils;
import com.pan.model.converter.user.UserVOConverter;
import com.pan.model.dto.user.UserDTO;
import com.pan.model.enums.login.Type;
import com.pan.model.req.login.GiteeLoginReq;
import com.pan.model.req.user.UserLoginReq;
import com.pan.model.req.user.UserRegisterReq;
import com.pan.model.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-04 05:18
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("/sys")
@RequiredArgsConstructor
public class LoginController {
    private final LoginBy3rd loginBy3rd;
    private final GiteeLoginHandler giteeLoginHandler;
    @Resource(name = "loginServiceImpl")
    private LoginService loginService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(
        @RequestBody @Validated UserRegisterReq userRegisterReq) {
        if (userRegisterReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        String username = userRegisterReq.getUsername();
        String password = userRegisterReq.getPassword();
        String checkPassword = userRegisterReq.getCheckPassword();
        if (StringUtils.isAnyBlank(username, password, checkPassword)) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        long id = loginService.userRegister(username, password, checkPassword);
        return ResultUtils.success(id);
    }

    @LoginLog(loginType = Type.DEFAULT, username = "#userLoginReq.username")
    @PostMapping("/login")
    public BaseResponse<String> userLogin(
        @RequestBody @Validated UserLoginReq userLoginReq) {
        String username = userLoginReq.getUsername();
        String password = userLoginReq.getPassword();

        String token = loginService.userLogin(username, password);
        return ResultUtils.success(token);
    }

    @GetMapping("/login/3rd-url/{type}")
    public BaseResponse<String> getThirdLoginUrl(
        @PathVariable("type") @NotNull Integer type) {
        String giteeLoginUrl = LoginBy3rdUrlFactory.getUrl(type);

        return ResultUtils.success(giteeLoginUrl);
    }

    @LoginLog(loginType = Type.GITEE)
    @PostMapping("/login/gitee")
    public BaseResponse<String> userLoginByGitee(
        @RequestBody @Validated GiteeLoginReq giteeLoginReq) {
        if (!Objects.equals(giteeLoginHandler.getState(), giteeLoginReq.getState())) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        String token = loginBy3rd.userLoginByGitee(giteeLoginReq.getCode());
        return ResultUtils.success(token);
    }

    @PostMapping("/login/qq")
    public BaseResponse<String> userLoginByQQ(String state, String code) {
        String token = loginBy3rd.userLoginByQQ(state, code);
        return ResultUtils.success(token);
    }

    @SaCheckLogin
    @PostMapping("/logout")
    public BaseResponse<Void> userLogout() {
        loginService.userLogout();
        return ResultUtils.success();
    }

    @SaCheckLogin
    @GetMapping("/user")
    public BaseResponse<UserVO> getLoginUser() {
        UserDTO userDTO = AuthUtils.getLoginUser();
        UserVO userVO = UserVOConverter.INSTANCE.toUserVO(userDTO);

        return ResultUtils.success(userVO);
    }
}
