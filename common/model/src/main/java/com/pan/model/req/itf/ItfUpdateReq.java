package com.pan.model.req.itf;

import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.itf.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 16:30
 **/
@Data
public class ItfUpdateReq implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    
    /**
     * 接口名称
     */
    private String name;
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
    private Long userId;

    private static final long serialVersionUID = 1L;

}
