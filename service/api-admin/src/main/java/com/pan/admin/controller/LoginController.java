package com.pan.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.pan.admin.service.LoginService;
import com.pan.common.util.AuthUtils;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.common.resp.ResultUtils;
import com.pan.model.converter.user.UserVOConverter;
import com.pan.model.dto.user.UserDTO;
import com.pan.model.req.user.UserLoginReq;
import com.pan.model.req.user.UserRegisterReq;
import com.pan.model.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-04 05:18
 **/
@Validated
@RestController
@RequestMapping("/sys")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

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

    @PostMapping("/login")
    public BaseResponse<String> userLogin(
        @RequestBody @Validated UserLoginReq userLoginReq) {
        if (userLoginReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }
        String username = userLoginReq.getUsername();
        String password = userLoginReq.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        String token = loginService.userLogin(username, password);

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
