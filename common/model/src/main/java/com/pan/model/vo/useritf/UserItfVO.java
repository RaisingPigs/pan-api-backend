package com.pan.model.vo.useritf;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserItfVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long itfId;

    private Integer invokeCount;

    private Integer leftCount;

    private Integer status;
}