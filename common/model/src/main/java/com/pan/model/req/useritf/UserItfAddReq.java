package com.pan.model.req.useritf;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 16:31
 **/
@Data
public class UserItfAddReq implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long itfId;

    /**
     * 总调用次数
     */
    private Integer invokeCount;

    /**
     * 剩余调用次数
     */
    private Integer leftCount;


    private static final long serialVersionUID = 1L;
}
