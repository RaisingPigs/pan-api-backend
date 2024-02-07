package com.pan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pan.model.common.entity.BaseEntity;
import com.pan.model.enums.user.GenderEnum;
import com.pan.model.enums.user.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户
 * @TableName user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户昵称
     */
    @TableField("name")
    private String name;
    /**
     * 账号
     */
    @TableField("username")
    private String username;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;


    @TableField("access_key")
    private String accessKey;
    
    @TableField("secret_key")
    private String secretKey;

    /**
     * 性别
     */
    @TableField("gender")
    private GenderEnum gender;
    /**
     * 用户角色：user / admin
     */
    @TableField("role")
    private RoleEnum role;
}