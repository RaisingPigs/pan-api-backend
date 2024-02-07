package com.pan.model.vo.itf;

import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.itf.StatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 接口信息表
 * @TableName interface_info
 */
@Data
public class ItfVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String path;

    private String url;

    private MethodEnum method;

    private String description;

    private String queryParamExample;

    private String bodyParamExample;

    private String respExample;
    
    private String reqHeader;

    private String respHeader;

    private StatusEnum status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}