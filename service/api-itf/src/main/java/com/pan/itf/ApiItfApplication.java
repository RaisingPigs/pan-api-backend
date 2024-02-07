package com.pan.itf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.pan.itf", "com.pan.common"})
@MapperScan("com.pan.itf.mapper")
@EnableDiscoveryClient
public class ApiItfApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiItfApplication.class, args);
    }

}
