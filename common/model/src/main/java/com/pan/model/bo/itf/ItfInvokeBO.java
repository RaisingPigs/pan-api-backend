package com.pan.model.bo.itf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pan.model.entity.Itf;
import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.req.itf.ItfInvokeReq;
import lombok.Data;
import lombok.SneakyThrows;

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
    private Map<String, Object> queryParams;
    private String bodyParam;
    private MethodEnum method;

    public ItfInvokeBO() {
    }

    @SneakyThrows
    public ItfInvokeBO(Itf itf) {
        this.id = itf.getId();
        this.url = itf.getUrl();

        String queryParamStr = itf.getQueryParamExample();
        this.queryParams = new ObjectMapper().readValue(queryParamStr, new TypeReference<Map<String, Object>>() {
        });

        this.bodyParam = itf.getBodyParamExample();
        this.method = itf.getMethod();
    }

    public ItfInvokeBO(
        Itf itf,
        ItfInvokeReq itfInvokeRequest) {
        this.id = itf.getId();
        this.url = itf.getUrl();
        this.queryParams = itfInvokeRequest.getQueryParam();
        this.bodyParam = itfInvokeRequest.getBodyParam();
        this.method = itf.getMethod();
    }
}
