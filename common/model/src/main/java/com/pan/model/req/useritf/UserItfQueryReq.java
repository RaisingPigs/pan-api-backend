package com.pan.model.req.useritf;

import com.pan.model.common.req.PageReq;
import com.pan.model.enums.useritf.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 16:22
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserItfQueryReq extends PageReq implements Serializable {
    /**
     * 主键id
     */
    private Long id;

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

    /**
     * 状态 0-正常 1-禁用(用户违反规则后禁用)
     */
    private StatusEnum status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

}
