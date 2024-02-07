package com.pan.model.req.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-06 09:18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessKeyReq {
    private String accessKey;
}
