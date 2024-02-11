package com.pan.admin.controller;

import com.pan.admin.service.StatisticsService;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultUtils;
import com.pan.model.vo.statistics.StatisticsCountVO;
import com.pan.model.vo.statistics.StatisticsItfCountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-11 08:19
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    
    @GetMapping("/count")
    public BaseResponse<StatisticsCountVO> count() {
        int itfCount = statisticsService.countItf();
        int invokeCount = statisticsService.countInvoke();
        int userInvokeCount = statisticsService.countUserInvoke();
        int userLeftCount = statisticsService.countUserLeft();

        StatisticsCountVO statisticsCountVO = new StatisticsCountVO(itfCount, invokeCount, userInvokeCount, userLeftCount);
        return ResultUtils.success(statisticsCountVO);
    }

    @GetMapping("/count/invoke_top")
    public BaseResponse<List<StatisticsItfCountVO>> countInvokeTopN() {
        List<StatisticsItfCountVO> list = statisticsService.countInvokeTopN();

        return ResultUtils.success(list);
    }

    @GetMapping("/count/user_invoke_top")
    public BaseResponse<List<StatisticsItfCountVO>> countUserInvokeTopN() {
        List<StatisticsItfCountVO> list = statisticsService.countUserInvokeTopN();

        return ResultUtils.success(list);
    }
}
