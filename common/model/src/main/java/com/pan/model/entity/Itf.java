package com.pan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pan.model.common.entity.BaseEntity;
import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.itf.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接口信息表
 * @TableName interface_info
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "itf")
@Data
public class Itf extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.NONE)
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口路径
     */
    private String path;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求类型: 0-get 1-post 2-put 3-delete
     */
    private MethodEnum method;

    /**
     * 接口描述
     */
    private String description;

    /**
     * query参数 {"name": "qwe"}
     */
    @TableField("query_param_example")
    private String queryParamExample;

    /**
     * body参数 {"name": "qwe"}
     */
    @TableField("body_param_example")
    private String bodyParamExample;

    /**
     * 响应结果 {"code": 0, "data": {}, "msg": "success"}
     */
    @TableField("resp_example")
    private String respExample;

    /**
     * 请求头
     */
    @TableField("req_header")
    private String reqHeader;

    /**
     * 响应头
     */
    @TableField("resp_header")
    private String respHeader;

    /**
     * 接口状态: 0-关闭 1-开启
     */
    private StatusEnum status;
}