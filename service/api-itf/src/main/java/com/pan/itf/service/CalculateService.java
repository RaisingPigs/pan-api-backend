package com.pan.itf.service;

import com.pan.itf.model.vo.BmiVO;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-14 16:46
 **/
public interface CalculateService {
    BmiVO calculateBmi(double weight, double height);
}
