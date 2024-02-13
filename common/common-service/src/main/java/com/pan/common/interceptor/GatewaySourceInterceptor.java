package com.pan.common.interceptor;

import com.pan.model.constant.GatewayConstant;
import com.pan.common.resp.ResultCode;
import com.pan.common.resp.ResultUtils;
import com.pan.common.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 22:15
 **/
@Component
@RequiredArgsConstructor
public class GatewaySourceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqSource = request.getHeader(GatewayConstant.REQ_SOURCE_KEY);

        if (!Objects.equals(reqSource, GatewayConstant.REQ_SOURCE_VALUE)) {
            WebUtils.respMsg(response, ResultUtils.failed(ResultCode.NO_AUTH, "内部系统, 禁止外部访问"));
            return false;
        }

        return true;
    }
}
