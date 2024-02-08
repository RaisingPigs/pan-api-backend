package com.pan.model.req.itf;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-27 08:59
 **/
@Data
public class ItfInvokeReq implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * url参数
     */
    private Map<String, Object> queryParam;

    /**
     * body参数
     */
    private Map<String, Object> bodyParam;
    
    private static final long serialVersionUID = 1L;
}
