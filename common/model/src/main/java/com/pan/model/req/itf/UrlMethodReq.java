package com.pan.model.req.itf;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-06 09:06
 **/
@Data
public class UrlMethodReq {
    @NotBlank(message = "数据不能为空")
    private String url;
    
    @NotBlank(message = "数据不能为空")
    private String method;
}
