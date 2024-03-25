package com.pan.admin.service;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-22 15:58
 **/
public interface LoginBy3rd {
    String userLoginByGitee(String state, String code);

    String userLoginByQQ(String state, String code);
}
