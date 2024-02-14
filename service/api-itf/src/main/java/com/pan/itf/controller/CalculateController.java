package com.pan.itf.controller;

import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultUtils;
import com.pan.itf.model.req.BmiReq;
import com.pan.itf.model.vo.BmiVO;
import com.pan.itf.service.CalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-14 16:36
 **/
@Validated
@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class CalculateController {
    private final CalculateService calculateService;

    @GetMapping("/bmi")
    public BaseResponse<BmiVO> calculateBmi(
        BmiReq bmiReq) {
        BmiVO bmiVO = calculateService.calculateBmi(bmiReq.getWeight(), bmiReq.getHeight());

        return ResultUtils.success(bmiVO);
    }
}
