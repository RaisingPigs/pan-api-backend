package com.pan.client;

import com.pan.common.resp.BaseResponse;
import com.pan.model.entity.Itf;
import com.pan.model.req.itf.PathMethodReq;
import com.pan.model.req.itf.UrlMethodReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-06 11:32
 **/
@FeignClient(value = "api-admin", contextId = "api-admin-itf", path = "/api/admin/itf")
public interface ItfFeignClient {
    @PostMapping("/url-method")
    BaseResponse<Itf> getItfByUrlAndMethod(
        @RequestBody UrlMethodReq urlMethodReq);

    @PostMapping("/path-method")
    BaseResponse<Itf> getItfByPathAndMethod(
        @RequestBody PathMethodReq pathMethodReq);
}
