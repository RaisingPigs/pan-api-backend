package com.pan.admin.aop;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.pan.admin.annotation.LoginLog;
import com.pan.admin.event.LoginLogEvent;
import com.pan.common.resp.BaseResponse;
import com.pan.common.util.AuthUtils;
import com.pan.common.util.EventPublishUtils;
import com.pan.model.constant.RequestConstant;
import com.pan.model.dto.user.UserDTO;
import com.pan.model.entity.SysLoginLog;
import com.pan.model.enums.login.State;
import com.pan.model.enums.login.Type;
import com.pan.sdk.util.SpelUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-27 20:44
 **/
@Aspect
@Component
public class LoginLogAop {
    @Pointcut(value = "@annotation(loginLog)", argNames = "loginLog")
    public void loginLogAspect(LoginLog loginLog) {
    }

    @AfterReturning(value = "loginLogAspect(loginLog)", argNames = "joinPoint,loginLog,returnValue", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, LoginLog loginLog, BaseResponse<?> returnValue) {
        UserDTO loginUser = AuthUtils.getLoginUser();
        Long userId = loginUser.getId();
        String username = loginUser.getUsername();
        State status = State.of(returnValue.successful());
        String msg = returnValue.getMsg();

        SysLoginLog sysLoginLog = createSysLoginLog(userId, username, loginLog, status, msg);
        EventPublishUtils.publishEvent(new LoginLogEvent<>(this, sysLoginLog));
    }

    @AfterThrowing(value = "loginLogAspect(loginLog)", argNames = "joinPoint,loginLog,e", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, LoginLog loginLog, Exception e) {
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();

        String username = SpelUtils.parse(loginLog.username(), paramNames, paramValues);

        SysLoginLog sysLoginLog = createSysLoginLog(null, username, loginLog, State.SUCCESS, e.getMessage());

        EventPublishUtils.publishEvent(new LoginLogEvent<>(this, sysLoginLog));
    }

    private SysLoginLog createSysLoginLog(Long userId, String username, LoginLog loginLog, State status, String msg) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "requestAttributes为null");
        HttpServletRequest request = requestAttributes.getRequest();

        Type loginType = loginLog.loginType();
        String ip = ServletUtil.getClientIP(request);
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader(RequestConstant.USER_AGENT));
        String browser = userAgent.getBrowser().getName();
        String os = userAgent.getOs().getName();

        return new SysLoginLog(userId, username, loginType, ip, browser, os, status, msg);
    }
}
