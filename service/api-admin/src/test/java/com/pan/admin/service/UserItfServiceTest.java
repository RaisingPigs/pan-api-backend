package com.pan.admin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-03 22:54
 **/
@SpringBootTest
public class UserItfServiceTest {
    @Resource
    private UserItfService userItfService;

    @Test
    void test() {
        userItfService.invokeCountIncrement(1629864820662681601L, 1L);
    }
}
