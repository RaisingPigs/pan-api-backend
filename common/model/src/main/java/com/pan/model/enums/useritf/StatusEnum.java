package com.pan.model.enums.useritf;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pan.model.enums.BaseEnum;
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
 * @create: 2023-02-26 21:43
 **/
@Getter
public enum StatusEnum implements BaseEnum {
    ENABLE("启用", 0),
    DISABLE("禁用", 1);

    private static final Map<Integer, StatusEnum> VALUE_MAP = Arrays.stream(values())
        .collect(Collectors.toMap(
            StatusEnum::getCode,
            Function.identity(),
            (statusEnum1, statusEnum2) -> statusEnum1
        ));
    @EnumValue
    private final int code;
    @JsonValue
    private final String desc;

    StatusEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public static StatusEnum of(int code) {
        return VALUE_MAP.computeIfAbsent(code, __ -> {
            throw new RuntimeException(code + "不存在");
        });
    }

    public static boolean isEnable(int code) {
        return StatusEnum.ENABLE == of(code);
    }

    public static boolean isDisable(int code) {
        return StatusEnum.DISABLE == of(code);
    }

    public static List<Integer> getCodes() {
        return Arrays.stream(values())
            .map(item -> item.code)
            .collect(Collectors.toList());
    }

    public boolean isEnable() {
        return Objects.equals(this, StatusEnum.ENABLE);
    }

    public boolean isDisable() {
        return !isEnable();
    }

}
