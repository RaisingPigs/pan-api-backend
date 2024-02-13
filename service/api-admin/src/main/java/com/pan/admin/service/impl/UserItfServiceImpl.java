package com.pan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pan.admin.mapper.UserItfMapper;
import com.pan.admin.service.UserItfService;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import com.pan.common.util.AuthUtils;
import com.pan.model.entity.UserItf;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Mr.Pan
 * @description 针对表【user_interface_info(用户接口调用表)】的数据库操作Service实现
 * @createDate 2023-03-01 09:46:46
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserItfServiceImpl
    extends ServiceImpl<UserItfMapper, UserItf>
    implements UserItfService {
    private final UserItfMapper userItfMapper;

    @Override
    public void validUserItf(UserItf userItf, boolean add) {
        if (userItf == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Long userId = userItf.getUserId();
        Long itfId = userItf.getItfId();

        // 创建时，所有参数必须非空
        if (add) {
            if (!ObjectUtils.anyNotNull(userId, itfId)) {
                throw new BusinessException(ResultCode.PARAMS_ERR, "参数不能为空");
            }
        }

        if (userItf.getInvokeCount() < 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "调用次数不能小于0");
        }
        if (userItf.getLeftCount() < 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "剩余可调用次数不能小于0");
        }
    }

    @Override
    public void invokeCountIncrement(long itfId, long userId) {
        int i = userItfMapper.itfInvokeCountIncrement(itfId, userId);

        if (i <= 0) {
            throw new BusinessException(ResultCode.SYSTEM_ERR);
        }
    }

    @Override
    public void checkInvokeAuth(Long itfId, Long userId) {
        UserItf userItf = getUserItf(itfId, userId);

        if (Objects.isNull(userItf)) {
            saveDefault(userId, itfId);
        } else {
            if (userItf.getStatus().isDisable()) {
                throw new BusinessException(ResultCode.NO_AUTH, "禁止调用该接口");
            }
            if (userItf.getLeftCount() <= 0) {
                throw new BusinessException(ResultCode.NO_AUTH, "剩余可调用次数不足");
            }
        }
    }

    @Override
    public UserItf getUserItfByItfId(Long itfId) {
        return getUserItf(itfId, AuthUtils.getLoginUserId());
    }

    @Override
    public UserItf getUserItf(Long itfId, Long userId) {
        return lambdaQuery()
            .eq(UserItf::getItfId, itfId)
            .eq(UserItf::getUserId, userId)
            .one();
    }

    @Override
    public void leftCountIncrementById(Long id, Integer count) {
        userItfMapper.leftCountIncrementById(id, count);
    }

    @Override
    public void leftCountIncrement(Long itfId, Long userId, Integer count) {
        UserItf userItf = getUserItf(itfId, userId);
        if (Objects.isNull(userItf)) {
            saveDefault(userId, itfId);
        }

        userItfMapper.leftCountIncrement(itfId, userId, count);
    }

    private void saveDefault(Long userId, Long itfId) {
        UserItf newUserItf = new UserItf(userId, itfId);
        save(newUserItf);
    }
}




