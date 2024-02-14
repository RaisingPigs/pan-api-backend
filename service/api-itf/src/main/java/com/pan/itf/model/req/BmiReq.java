package com.pan.itf.model.req;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-14 16:41
 **/
@Data
public class BmiReq {
    @NotNull
    @Min(value = 1, message = "体重不合法")
    @Max(value = 500, message = "体重不合法")
    private Double weight;

    @NotNull
    @Min(value = 1, message = "身高不合法")
    @Max(value = 3, message = "身高不合法")
    private Double height;
}
