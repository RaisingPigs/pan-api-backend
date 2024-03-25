package com.pan.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import com.pan.common.resp.ResultUtils;
import com.pan.common.util.JSONUtils;
import com.pan.model.enums.user.RoleEnum;
import com.pan.sdk.constant.HeaderConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class SaTokenConfig {
    private static final String[] GLOBAL_WHITE_LIST = {
        "/api/admin/sys/login",
        "/api/admin/sys/login/3rd-url",
        "/api/admin/sys/login/gitee",
        "/api/admin/sys/login/qq",
        "/api/admin/sys/register"
    };

    // admin下的接口需要登录才能访问
    private static final String[] LOGIN_CHECK_LIST = {
        "/api/admin/sys/logout",
        "/api/admin/sys/user",
        "/api/admin/user/ak-sk"
    };

    private static final String[] ADMIN_CHECK_LIST = {
        "/api/admin/**"
    };

    private static final String[] USER_CHECK_LIST = {
        "/api/admin/itf/list/page",
        "/api/admin/itf/invoke",
        "/api/admin/itf/get/*",
        "/api/admin/itf/details/*",
        "/api/admin/user-itf/get/*",
        "/api/admin/user-itf/get",
        "/api/admin/user-itf/count-increment/left",
        "/api/admin/statistics/count",
        "/api/admin/statistics/count/invoke_top",
        "/api/admin/statistics/count/user_invoke_top"
    };

    private static final String[] CLIENT_CHECK_LIST = {
        "/api/admin/user/user-by-ak",
        "/api/admin/user/sk-by-ak",
        "/api/admin/itf/url-method",
        "/api/admin/itf/path-method",
        "/api/admin/user-itf/count-increment/invoke",
        "/api/admin/user-itf/check-invoke-auth"
    };

    // 注册 Sa-Token全局过滤器 
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
            // 拦截全部path
            .addInclude("/**")
            .addExclude(GLOBAL_WHITE_LIST)
            .setAuth(obj -> {
                SaRouter.match(CLIENT_CHECK_LIST)
                    .check(r -> {
                        boolean roleCheckRes = StpUtil.hasRole(RoleEnum.ADMIN.getDesc());

                        if (roleCheckRes) {
                            return;
                        }

                        ServerHttpRequest request = SaReactorSyncHolder.getContext().getRequest();
                        String val = request.getHeaders().getFirst(HeaderConstant.CLIENT_KEY);
                        boolean headerCheckRes = Objects.equals(val, HeaderConstant.CLIENT_VAL);

                        if (headerCheckRes) {
                            return;
                        }

                        throw new BusinessException(ResultCode.NO_AUTH);
                    })
                    .stop();

                // 登录校验
                SaRouter.match(LOGIN_CHECK_LIST)
                    .check(r -> StpUtil.checkLogin())
                    .stop();

                SaRouter.match(USER_CHECK_LIST)
                    .check(r -> StpUtil.checkRoleOr(RoleEnum.USER.getDesc(), RoleEnum.ADMIN.getDesc()))
                    .stop();

                // 角色校验
                SaRouter.match(ADMIN_CHECK_LIST)
                    .check(r -> StpUtil.checkRole(RoleEnum.ADMIN.getDesc()))
                    .stop();
            })
            .setBeforeAuth(obj -> {
                SaHolder.getResponse()
                    // 允许指定域访问跨域资源
                    .setHeader("Access-Control-Allow-Origin", "*")
                    // 允许所有请求方式
                    .setHeader("Access-Control-Allow-Methods", "*")
                    // 允许的header参数
                    .setHeader("Access-Control-Allow-Headers", "*")
                    // 有效时间
                    .setHeader("Access-Control-Max-Age", "3600");

                // 如果是预检请求，则立即返回到前端 
                SaRouter.match(SaHttpMethod.OPTIONS).back();
            })
            .setError(e -> {
                if (e instanceof NotLoginException) {
                    return JSONUtils.toJsonStr(ResultUtils.failed(new BusinessException(ResultCode.NO_LOGIN)));
                }

                return JSONUtils.toJsonStr(ResultUtils.failed(new BusinessException(ResultCode.NO_AUTH)));
            });
    }
}