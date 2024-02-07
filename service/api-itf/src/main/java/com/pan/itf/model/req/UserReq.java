package com.pan.itf.model.req;

import com.pan.model.enums.user.GenderEnum;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-07 08:23
 **/
@Data
public class UserReq {
    private String username;
    private GenderEnum gender;
    private List<Grade> grades;


    @Data
    static class Grade {
        private String course;
        private Integer score;
    }
}
