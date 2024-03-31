package com.pan.model.enums.login;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-23 15:40
 **/
@Getter
public enum Type {
    DEFAULT(0, "default"),
    GITEE(1, "gitee");

    @EnumValue
    private final int code;
    @JsonValue
    private final String desc;

    Type(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Type{" +
            "code=" + code +
            ", desc='" + desc + '\'' +
            '}';
    }
}
