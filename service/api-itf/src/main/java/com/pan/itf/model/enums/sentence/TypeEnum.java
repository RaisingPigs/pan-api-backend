package com.pan.itf.model.enums.sentence;

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
 * @create: 2023-02-21 21:41
 **/
@Getter
public enum TypeEnum implements BaseEnum {
    CHICKEN_SOUP(0, "毒鸡汤"),
    LOVE_TALK(1, "土味情话"),
    JOKE(2, "笑话");

    private static final Map<Integer, TypeEnum> VALUE_MAP = Arrays.stream(values())
        .collect(Collectors.toMap(
            TypeEnum::getCode,
            Function.identity(),
            (enum1, enum2) -> enum1
        ));

    @EnumValue
    private final int code;
    @JsonValue
    private final String desc;


    TypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TypeEnum of(int code) {
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
