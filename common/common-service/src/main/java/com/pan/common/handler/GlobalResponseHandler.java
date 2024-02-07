package com.pan.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

@Slf4j
@RestControllerAdvice(basePackages = {
    "com.pan.admin.controller",
    "com.pan.itf.controller"
})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper objectMapper;

    /**
     * @return 返回 true 则下面 beforeBodyWrite方法被调用, 否则就不调用下述方法
     */
    @Override
    public boolean supports(
        MethodParameter returnType,
        Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是BaseResponse那就没有必要进行额外的操作，返回false
        return !returnType.getParameterType().equals(BaseResponse.class);
    }

    @Override
    public Object beforeBodyWrite(
        Object data,
        MethodParameter returnType,
        MediaType mediaType,
        Class<? extends HttpMessageConverter<?>> aClass,
        ServerHttpRequest request,
        ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            return convert2BaseResponse(data);
        }

        // 将原本的数据包装在BaseResponse里
        return ResultUtils.success(data);
    }

    /**
     * 如果data可以转换为BaseResponse, 说明是子系统返回的json数据, 则直接返回
     * 如果data不能转换为BaseResponse, 说明接口返回的就是普通字符串, 则ResultUtils包装后转为json返回
     */
    @SneakyThrows
    private String convert2BaseResponse(Object data) {
        try {
            String dataStr = data.toString();
            BaseResponse<?> baseResponse = objectMapper.readValue(dataStr, BaseResponse.class);
            if (baseResponse != null) {
                return dataStr;
            } else {
                return objectMapper.writeValueAsString(ResultUtils.success(data));
            }
        } catch (JsonProcessingException e) {
            return objectMapper.writeValueAsString(ResultUtils.success(data));
        }
    }
}
