package com.pan.model.enums.login;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-23 15:40
 **/
@Getter
public enum TypeEnum {
    GITEE("gitee");

    @JsonValue
    private final String desc;

    TypeEnum(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TypeEnum{" +
            "desc='" + desc + '\'' +
            '}';
    }
}
