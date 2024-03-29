package com.pan.common.exception;


import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import lombok.Getter;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-07 14:29
 **/
@Getter
public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(BaseResponse<?> baseResponse) {
        super(baseResponse.getMsg());
        this.code = baseResponse.getCode();
    }

    public BusinessException(ResultCode resultCode, String msg) {
        super(msg);
        this.code = resultCode.getCode();
    }

    public BusinessException(ResultCode resultCode) {
        this(resultCode, resultCode.getMsg());
    }

}
