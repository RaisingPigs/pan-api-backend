package com.pan.gateway.test;

import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.model.entity.Itf;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-05 22:28
 **/
public class TestWithoutBoot {
    @SneakyThrows
    @Test
    void test() {
        WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:9999/api/admin")
            .defaultHeader("source", "pai-api-gateway")
            .build();

        Mono<BaseResponse<Itf>> interfaceInfoMono = webClient.get()
            .uri("/interface-info/get/17")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<BaseResponse<Itf>>() {
            }) 
            .filter(baseResponse -> baseResponse != null && ResultCode.isSuccessful(baseResponse.getCode()) && Objects.nonNull(baseResponse.getData()));;
        interfaceInfoMono.subscribe(System.out::println);

        // webClient.get()
        //     .uri("/interface-info/get/1")
        //     .retrieve()
        //     .toEntity(new ParameterizedTypeReference<BaseResponse<InterfaceInfo>>() {
        //     })
        //     .subscribe(resp -> {
        //         BaseResponse<InterfaceInfo> body = resp.getBody();
        //         System.out.println(body);
        //     });
        
        TimeUnit.MINUTES.sleep(1);
    }
}
