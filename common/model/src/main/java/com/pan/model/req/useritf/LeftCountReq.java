package com.pan.model.req.useritf;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-10 14:36
 **/
@Data
public class LeftCountReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long itfId;
    private Integer count;
}
