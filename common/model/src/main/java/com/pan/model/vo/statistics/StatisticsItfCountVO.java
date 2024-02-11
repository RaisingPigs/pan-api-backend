package com.pan.model.vo.statistics;

import lombok.Data;

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
    private Integer percentage;
}
