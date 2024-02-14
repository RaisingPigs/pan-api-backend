package com.pan.itf.model.vo;

import com.pan.itf.model.enums.calculate.BMIEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-14 16:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmiVO {
    private double bmi;
    private String level;
    private String normalBMI;

    public BmiVO(double bmi) {
        this.bmi = bmi;
        this.level = BMIEnum.of(bmi).getDesc();
        this.normalBMI = "18.5 ~ 23.9";
    }
}
