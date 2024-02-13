package com.pan.client;

import com.pan.common.resp.BaseResponse;
import com.pan.model.req.useritf.InvokeAuthCheckReq;
import com.pan.model.req.useritf.InvokeCountReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-06 11:01
 **/
@FeignClient(value = "api-admin", contextId = "api-admin-userItf", path = "/api/admin/user-itf")
public interface UserItfFeignClient {
    @PostMapping("/check-invoke-auth")
    BaseResponse<Void> checkInvokeAuth(@RequestBody InvokeAuthCheckReq invokeAuthCheckReq);

    @PutMapping("/count-increment/invoke")
    BaseResponse<Void> invokeCountIncrement(@RequestBody InvokeCountReq invokeCountReq);
}
