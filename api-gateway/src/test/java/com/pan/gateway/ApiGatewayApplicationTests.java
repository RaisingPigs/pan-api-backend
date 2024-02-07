package com.pan.gateway;

import com.pan.client.ItfFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiGatewayApplicationTests {
    @Resource
    ItfFeignClient itfFeignClient;

    @Test
    void contextLoads() {
        // BaseResponse<InterfaceInfo> res = interfaceInfoFeignClient.getInterfaceInfoByUrlAndMethod("/api/itf/name/post/json", "POST");
        // System.out.println(res);
    }


}
