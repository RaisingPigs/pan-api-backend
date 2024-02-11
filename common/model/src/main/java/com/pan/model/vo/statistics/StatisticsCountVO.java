package com.pan.model.vo.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-11 08:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsCountVO {
    private int itfCount;
    private int invokeCount;
    private int userInvokeCount;
    private int userLeftCount;
}
