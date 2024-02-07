package com.pan.sdk.util;

import com.pan.sdk.constant.SignConstant;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-23 11:30
 **/
public class SignUtils {
    /**
     * 加密创建sign
     * @param map 请求头参数(用户参数, ak, nonce随机值, 时间戳)
     * @param secretKey sk
     */
    public static String createSign(Map<String, String> map, String secretKey) {
        String content = map.toString() + "." + secretKey;
        return DigestUtils.md5DigestAsHex(content.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * 生成请求头map
     */
    public static Map<String, String> getHeaderMap(
        String accessKey, String secretKey) {
        Map<String, String> map = new HashMap<>();
        map.put(SignConstant.ACCESS_KEY, accessKey);
        map.put(SignConstant.NONCE, String.valueOf(new Random().nextInt(SignConstant.NONCE_MAX_VALUE)));
        map.put(SignConstant.TIMESTAMP, String.valueOf(Instant.now().getEpochSecond()));

        map.put(SignConstant.SIGN, createSign(map, secretKey));

        return map;
    }

    /**
     * 校验sign
     * @param signGot
     * @param accessKey
     * @param secretKey
     * @param nonce
     * @param timestamp
     */
    public static boolean checkSign(String signGot, String accessKey, String secretKey, String nonce, long timestamp) {
        Map<String, String> map = new HashMap<>();
        map.put(SignConstant.ACCESS_KEY, accessKey);
        map.put(SignConstant.NONCE, nonce);
        map.put(SignConstant.TIMESTAMP, String.valueOf(timestamp));

        String sign = createSign(map, secretKey);

        return signGot != null && signGot.equals(sign);
    }
}
