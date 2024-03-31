package com.pan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pan.model.common.entity.BaseEntity;
import com.pan.model.enums.param.ParamTypeEnum;
import com.pan.model.enums.param.RequiredEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 请求参数表
 * @TableName param
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "param")
public class Param extends BaseEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * interface的id
     */
    @TableField("itf_id")
    private Long itfId;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 参数名
     */
    private String name;

    /**
     * 是否必填, 0否 1是
     */
    private RequiredEnum required;

    /**
     * 数据类型
     */
    @TableField("data_type")
    private String dataType;

    /**
     * 描述
     */
    private String description;

    /**
     * 参数类型, 0-路径参数 1-body参数 2-返回值
     */
    @TableField("param_type")
    private ParamTypeEnum paramType;
}