package com.pan.model.req.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-25 17:34
 **/
@Data
public class GiteeLoginReq {
    @NotBlank(message = "code不能为空")
    private String code;
    
    @NotBlank(message = "state不能为空")
    private String state;
}
