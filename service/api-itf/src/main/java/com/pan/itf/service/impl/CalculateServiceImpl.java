package com.pan.itf.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.pan.itf.model.vo.BmiVO;
import com.pan.itf.service.CalculateService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-14 16:46
 **/
@Service
public class CalculateServiceImpl implements CalculateService {
    @Override
    public BmiVO calculateBmi(double weight, double height) {
        double bmi = NumberUtil.round(weight / Math.pow(height, 2), 2).doubleValue();
        return new BmiVO(bmi);
    }

}
