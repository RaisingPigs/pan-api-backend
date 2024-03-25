package com.pan.model.vo.login;

import com.pan.model.enums.login.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-23 15:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThirdUrlVO {
    private TypeEnum loginType;
    private String loginUrl;
}
