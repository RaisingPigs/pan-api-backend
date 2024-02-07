package com.pan.model.req.useritf;

import com.pan.model.enums.useritf.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 16:30
 **/
@Data
public class UserItfUpdateReq implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 剩余调用次数
     */
    private Integer leftCount;

    /**
     * 状态 0-正常 1-禁用(用户违反规则后禁用)
     */
    private StatusEnum status;

    private static final long serialVersionUID = 1L;
}
