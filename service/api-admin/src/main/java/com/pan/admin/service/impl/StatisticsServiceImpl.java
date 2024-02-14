package com.pan.admin.service.impl;

import com.pan.admin.mapper.UserItfMapper;
import com.pan.admin.service.ItfService;
import com.pan.admin.service.StatisticsService;
import com.pan.common.util.AuthUtils;
import com.pan.model.vo.statistics.StatisticsItfCountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-11 08:27
 **/
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final ItfService itfService;
    private final UserItfMapper userItfMapper;

    @Override
    public int countItf() {
        return (int) itfService.count();
    }

    @Override
    public int countInvoke() {
        Integer countedInvoke = userItfMapper.countInvoke();
        return Optional.ofNullable(countedInvoke).orElse(0);
    }

    @Override
    public int countUserInvoke() {
        Long loginUserId = AuthUtils.getLoginUserId();
        Integer userInvoke = userItfMapper.countUserInvoke(loginUserId);
        return Optional.ofNullable(userInvoke).orElse(0);
    }

    @Override
    public int countUserLeft() {
        Long loginUserId = AuthUtils.getLoginUserId();
        return userItfMapper.countUserLeft(loginUserId);
    }

    @Override
    public List<StatisticsItfCountVO> countInvokeTopN() {
        int total = countInvoke();
        List<StatisticsItfCountVO> statisticsItfCountVOList = userItfMapper.selectInvokeTop10();

        statisticsItfCountVOList.forEach(percentage -> percentage.calcPercentage(total));

        return statisticsItfCountVOList;
    }

    @Override
    public List<StatisticsItfCountVO> countUserInvokeTopN() {
        Long loginUserId = AuthUtils.getLoginUserId();
        int total = countUserInvoke();
        List<StatisticsItfCountVO> statisticsItfCountVOList = userItfMapper.selectUserInvokeTop10(loginUserId);

        statisticsItfCountVOList.forEach(percentage -> percentage.calcPercentage(total));

        return statisticsItfCountVOList;
    }
}
