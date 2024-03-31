package com.pan.admin.factory;

import com.pan.model.enums.login.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-31 18:12
 **/
public class LoginBy3rdUrlFactory {
    private static final Map<Type, String> URL_MAP = new HashMap<>();

    public static void add(Type type, String url) {
        URL_MAP.put(type, url);
    }

    public static String getUrl(int typeCode) {
        return URL_MAP.get(Type.of(typeCode));
    }
}
