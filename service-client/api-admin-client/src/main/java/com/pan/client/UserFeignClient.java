package com.pan.client;

import com.pan.common.resp.BaseResponse;
import com.pan.model.entity.User;
import com.pan.model.req.user.AccessKeyReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-06 09:52
 **/
@FeignClient(value = "api-admin", contextId = "api-admin-user", path = "/api/admin/user")
public interface UserFeignClient {
    @PostMapping("/user-by-ak")
    BaseResponse<User> getUserByAccessKey(@RequestBody AccessKeyReq accessKeyReq);

    @PostMapping("/sk-by-ak")
    BaseResponse<String> getSecretKeyByAccessKey(@RequestBody AccessKeyReq accessKeyReq);
}
