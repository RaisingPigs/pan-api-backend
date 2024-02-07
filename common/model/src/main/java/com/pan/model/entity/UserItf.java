package com.pan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pan.model.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户接口调用表
 * @TableName user_interface_info
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_itf")
@Data
public class UserItf extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 接口id
     */
    @TableField("itf_id")
    private Long itfId;

    /**
     * 总调用次数
     */
    @TableField("invoke_count")
    private Integer invokeCount;

    /**
     * 剩余调用次数
     */
    @TableField("left_count")
    private Integer leftCount;

    /**
     * 状态 0-正常 1-禁用(用户违反规则后禁用)
     */
    private Integer status;


    public UserItf() {
    }

    public UserItf(Long userId, Long itfId, Integer invokeCount, Integer leftCount) {
        this.userId = userId;
        this.itfId = itfId;
        this.invokeCount = invokeCount;
        this.leftCount = leftCount;
    }
}