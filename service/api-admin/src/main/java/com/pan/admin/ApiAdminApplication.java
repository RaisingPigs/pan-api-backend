package com.pan.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.pan.admin", "com.pan.common"})
@MapperScan("com.pan.admin.mapper")
@EnableDiscoveryClient
public class ApiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }
}
