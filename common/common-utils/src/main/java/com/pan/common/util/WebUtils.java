package com.pan.common.util;


import com.pan.common.resp.BaseResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-02 08:16
 **/
public class WebUtils {
    private static final String CONTENT_TYPE_VALUE = "application/json";
    
    public static void respMsg(
        HttpServletResponse response,
        BaseResponse<?> baseResponse) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(CONTENT_TYPE_VALUE);

        String json = JSONUtils.toJsonStr(baseResponse);
        response.getWriter().append(json);
    }
}
