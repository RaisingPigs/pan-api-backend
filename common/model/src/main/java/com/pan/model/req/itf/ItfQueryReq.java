package com.pan.model.req.itf;

import com.pan.model.common.req.PageReq;
import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.itf.StatusEnum;
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
public class ItfQueryReq extends PageReq implements Serializable {
    /**
     * 主键id
     */
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

    private String queryParamExample;

    private String bodyParamExample;

    private String respExample;
    
    /**
     * 请求头
     */
    private String reqHeader;

    /**
     * 响应头
     */
    private String respHeader;

    /**
     * 接口状态: 0-关闭 1-开启
     */
    private StatusEnum status;

    /**
     * 创建人id
     */
    private Long creatorId;

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
