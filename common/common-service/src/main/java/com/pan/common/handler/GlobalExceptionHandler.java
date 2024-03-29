package com.pan.common.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.common.resp.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-07 14:26
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERR_MSG_DELIMITER = ",";


    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("全局异常处理BusinessException: [{}]", e.getMessage(), e);
        return ResultUtils.failed(e);
    }

    @ExceptionHandler(SaTokenException.class)
    public BaseResponse<?> businessExceptionHandler(SaTokenException e) {
        // 不同异常返回不同状态码
        if (e instanceof NotLoginException) {               // 如果是未登录异常
            return ResultUtils.failed(ResultCode.NO_LOGIN);
        }
        if (e instanceof NotRoleException) {         // 如果是角色异常
            return ResultUtils.failed(ResultCode.NO_AUTH);
        }
        if (e instanceof NotPermissionException) {   // 如果是权限异常
            return ResultUtils.failed(ResultCode.NO_AUTH);
        }

        // 其他异常
        return ResultUtils.failed(ResultCode.NO_AUTH);
    }

    /**
     * 处理 json 请求体调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> jsonParamsException(MethodArgumentNotValidException e) {
        String errMsg = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(ERR_MSG_DELIMITER));

        log.error("全局异常处理MethodArgumentNotValidException: [{}]", errMsg, e);

        return ResultUtils.failed(ResultCode.SYSTEM_ERR, errMsg);
    }


    /**
     * 处理单个参数校验失败抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<?> ParamsException(ConstraintViolationException e) {
        String errMsg = e.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(ERR_MSG_DELIMITER));

        log.error("全局异常处理ConstraintViolationException: [{}]", errMsg, e);
        return ResultUtils.failed(ResultCode.SYSTEM_ERR, errMsg);
    }

    /**
     * @param e
     * @return 处理 form data方式调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse<?> formDaraParamsException(BindException e) {
        String errMsg = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(ERR_MSG_DELIMITER));

        log.error("全局异常处理BindException: [{}]", errMsg, e);
        return ResultUtils.failed(ResultCode.SYSTEM_ERR, errMsg);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> exceptionHandler(Exception e) {
        log.error("全局异常处理Exception: [{}]", e.getMessage(), e);
        return ResultUtils.failed(ResultCode.SYSTEM_ERR, e.getMessage());
    }
}
