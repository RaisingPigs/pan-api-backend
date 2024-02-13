package com.pan.model.vo.statistics;

import cn.hutool.core.util.NumberUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-11 08:55
 **/
@Data
public class StatisticsItfCountVO {
    private Long id;
    private String name;
    private Integer total;
    private Double percentage;

    public void calcPercentage(int total) {
        if (total == 0) {
            setPercentage(0D);
            return;
        }
        BigDecimal percentage = NumberUtil.round((double) getTotal() / total, 2);

        setPercentage(percentage.doubleValue());
    }
}
