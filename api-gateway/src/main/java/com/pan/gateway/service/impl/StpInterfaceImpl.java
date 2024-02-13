package com.pan.gateway.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.pan.model.constant.UserConstant;
import com.pan.model.dto.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();
        UserDTO userDTO = StpUtil.getSession().getModel(UserConstant.USER_LOGIN_STATE, UserDTO.class);
        list.add(userDTO.getRole().getDesc());

        return list;
    }

}