package com.pan.model.dto.user;

import com.pan.model.common.entity.BaseEntity;
import com.pan.model.enums.user.GenderEnum;
import com.pan.model.enums.user.RoleEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-26 23:41
 **/
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
   
    private String name;
  
    private String username;
   
    private String avatar;

    private String accessKey;

    private GenderEnum gender;
    
    private RoleEnum role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
