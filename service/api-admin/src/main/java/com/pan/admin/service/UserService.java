package com.pan.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pan.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author Mr.Pan
* @description 针对表【mp_user】的数据库操作Service
* @createDate 2023-02-21 16:25:21
*/
public interface UserService extends IService<User> {
    void validUser(User user, boolean add);

    boolean addUser(User user);

    boolean isAdmin();
}
