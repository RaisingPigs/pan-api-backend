package com.pan.model.enums.itf;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pan.model.enums.BaseEnum;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-06 12:12
 **/
@Getter
public enum MethodEnum  implements BaseEnum {
    GET("GET", 0),
    POST("POST", 1),
    PUT("PUT", 2),
    DELETE("DELETE", 3);

    @EnumValue
    private final int code;
    @JsonValue
    private final String desc;

    private static final Map<Integer, MethodEnum> VALUE_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    MethodEnum::getCode,
                    Function.identity(),
                    (enum1, enum2) -> enum1
            ));


    MethodEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public static MethodEnum of(int code) {
        return VALUE_MAP.computeIfAbsent(code, __ -> {
            throw new RuntimeException(code + "不存在");
        });
    }

}
