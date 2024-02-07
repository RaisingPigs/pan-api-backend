package com.pan.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pan.model.entity.UserItf;

/**
 * @author Mr.Pan
 * @description 针对表【user_interface_info(用户接口调用表)】的数据库操作Service
 * @createDate 2023-03-01 09:46:46
 */
public interface UserItfService extends IService<UserItf> {

    void validUserItf(UserItf userItf, boolean add);

    void invokeCountIncrement(long itfId, long userId);

    boolean checkInvokeAuth(Long itfId, Long userId);
}
