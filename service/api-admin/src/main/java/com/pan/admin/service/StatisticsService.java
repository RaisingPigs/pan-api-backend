package com.pan.admin.service;

import com.pan.model.vo.statistics.StatisticsItfCountVO;

import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-11 08:26
 **/
public interface StatisticsService {
    int countItf();
    
    int countInvoke();

    int countUserInvoke();

    int countUserLeft();

    List<StatisticsItfCountVO> countInvokeTopN();

    List<StatisticsItfCountVO> countUserInvokeTopN();
}
