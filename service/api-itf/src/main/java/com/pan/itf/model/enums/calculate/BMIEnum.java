package com.pan.itf.model.enums.calculate;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-14 16:59
 **/
@Getter
public enum BMIEnum {
    UNDERWEIGHT(0, 18.5, "过轻"),
    NORMAL_WEIGHT(18.5, 24, "正常"),
    OVERWEIGHT(24, 28, "过重"),
    OBESITY(28, 32, "肥胖"),
    SEVERE_OBESITY(32, Integer.MAX_VALUE, "重度肥胖");

    private final double min;
    private final double max;
    @JsonValue
    private final String desc;

    BMIEnum(double min, double max, String desc) {
        this.min = min;
        this.max = max;
        this.desc = desc;
    }

    public static BMIEnum of(double bmi) {
        if (bmi <= UNDERWEIGHT.getMax()) {
            return UNDERWEIGHT;
        } else if (bmi <= NORMAL_WEIGHT.getMax()) {
            return NORMAL_WEIGHT;
        } else if (bmi <= OVERWEIGHT.getMax()) {
            return OVERWEIGHT;
        } else if (bmi <= OBESITY.getMax()) {
            return OBESITY;
        } else {
            return SEVERE_OBESITY;
        }
    }
}
