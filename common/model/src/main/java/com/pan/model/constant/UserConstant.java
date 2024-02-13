package com.pan.model.constant;

/**
 * @description: 用户常量
 * @author: Mr.Pan
 * @create: 2023-02-21 22:11
 **/
public class UserConstant {
    public static final String USER_LOGIN_STATE = "currentUser";

    /*加盐*/
    public static final String SALT = "pan";

    public static final String DEFAULT_USERNAME_PREFIX = "默认用户名";
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String DEFAULT_AVATAR = "default";

    private UserConstant() {
    }
}
