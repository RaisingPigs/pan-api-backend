package com.pan.admin.service;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-04 05:23
 **/
public interface LoginService {
    long userRegister(String username, String password, String checkPassword);
    
    String userLogin(String username, String password);

    void userLogout();

}
