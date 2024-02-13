package com.pan.common.util;

import com.pan.common.resp.BaseResponse;

import java.util.Objects;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-06 00:03
 **/
public class BaseRespUtils {
    public static boolean isFailed(BaseResponse<?> baseResponse) {
        return !isSuccessful(baseResponse);
    }

    public static boolean isSuccessful(BaseResponse<?> baseResponse) {
        return Objects.nonNull(baseResponse) && baseResponse.successful();
    }
}
