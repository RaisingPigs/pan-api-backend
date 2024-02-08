package com.pan.model.bo.itf;

import com.pan.model.entity.Itf;
import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.req.itf.ItfInvokeReq;
import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-01-19 15:08
 **/
@Data
public class ItfInvokeBO {
    private Long id;
    private String url;
    private Map<String, Object> queryParam;
    private Map<String, Object> bodyParam;
    private MethodEnum method;

    public ItfInvokeBO() {
    }

    public ItfInvokeBO(
        Itf itf,
        ItfInvokeReq itfInvokeReq) {
        this.id = itf.getId();
        this.url = itf.getUrl();
        this.queryParam = itfInvokeReq.getQueryParam();
        this.bodyParam = itfInvokeReq.getBodyParam();
        this.method = itf.getMethod();
    }
}
