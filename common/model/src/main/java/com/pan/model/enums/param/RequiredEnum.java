package com.pan.model.enums.param;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-07 09:04
 **/
@Getter
public enum RequiredEnum {
    NOT_REQUIRED(0, "非必填"),
    REQUIRED(1, "必填");

    private static final Map<Integer, RequiredEnum> VALUE_MAP = Arrays.stream(values())
        .collect(Collectors.toMap(
            RequiredEnum::getCode,
            Function.identity(),
            (enum1, enum2) -> enum1
        ));

    @EnumValue
    private final int code;
    @JsonValue
    private final String desc;


    RequiredEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RequiredEnum of(int code) {
        return VALUE_MAP.computeIfAbsent(code, __ -> {
            throw new RuntimeException(code + "不存在");
        });
    }

    public static boolean valid(int code) {
        return Objects.nonNull(of(code));
    }


    public static List<Integer> getValues() {
        return Arrays.stream(values())
            .map(item -> item.code)
            .collect(Collectors.toList());
    }
}
