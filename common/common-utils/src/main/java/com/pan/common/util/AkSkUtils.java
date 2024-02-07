package com.pan.common.util;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-23 09:20
 **/
public class AkSkUtils {
    public static String getAccessKey(String content, String salt) {
        String s = salt + content + RandomUtils.nextInt(1, 10000);
        return DigestUtils.md5DigestAsHex(s.getBytes(StandardCharsets.UTF_8));
    }

    public static String getSecretKey(String content, String salt) {
        long epochSecond = Instant.now().getEpochSecond();
        String s = salt + content + epochSecond;
        return DigestUtils.md5DigestAsHex(s.getBytes(StandardCharsets.UTF_8));
    }
}
