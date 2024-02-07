package com.pan.common.constant;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-01-23 17:11
 **/
public class RedisConstant {
    public static final String USER_LOGIN_STATE = "pan-api:user:loginState";

    public static final String IP_BLACK_LIST = "pan-api:gateway:ipBlackList";

    public static final String NONCE_KEY = "pan-api:itf:nonce";
    

    private RedisConstant() {
    }
}
