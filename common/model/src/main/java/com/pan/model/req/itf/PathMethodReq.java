package com.pan.model.req.itf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-06 09:10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathMethodReq {
    @NotBlank(message = "数据不能为空")
    private String path;

    @NotBlank(message = "数据不能为空")
    private String method;
}
