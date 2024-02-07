package com.pan.admin;

import com.pan.sdk.client.ItfClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;

@SpringBootTest
class PanApiAdminApplicationTests {
    @Resource
    private ItfClient itfClient;

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            String s = itfClient.doGet("http://localhost:8888/api/itf/name/get", new HashMap<String, String>() {{
                put("name", "张三");
            }});
            System.out.println(s);
        }
    }

}
