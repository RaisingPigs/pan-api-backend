package com.pan.admin.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pan.admin.service.UserItfService;
import com.pan.common.constant.PageConstant;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.ResultCode;
import com.pan.common.util.AuthUtils;
import com.pan.model.converter.useritf.UserItfVOConverter;
import com.pan.model.entity.UserItf;
import com.pan.model.req.useritf.*;
import com.pan.model.vo.useritf.UserItfVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 16:48
 **/
@RestController
@RequestMapping("/user-itf")
public class UserItfController {
    @Resource
    private UserItfService userItfService;

    //region 增删改查
    @SaCheckRole("admin")
    @PostMapping("/add")
    public Long addUserItf(
        @RequestBody UserItfAddReq userItfAddReq) {
        if (userItfAddReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        UserItf userItf = new UserItf();
        BeanUtils.copyProperties(userItfAddReq, userItf);
        userItf.setUserId(AuthUtils.getLoginUser().getId());

        userItfService.validUserItf(userItf, true);
        boolean save = userItfService.save(userItf);
        if (!save) {
            throw new BusinessException(ResultCode.SAVE_ERR);
        }

        return userItf.getId();
    }

    @SaCheckRole("admin")
    @DeleteMapping("/delete/{id}")
    public void deleteUserItf(@PathVariable Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        UserItf oldUserItf = userItfService.getById(id);
        if (oldUserItf == null) {
            throw new BusinessException(ResultCode.NULL_ERR);
        }

        boolean b = userItfService.removeById(id);
        if (!b) {
            throw new BusinessException(ResultCode.DELETE_ERR);
        }
    }

    @SaCheckRole("admin")
    @PutMapping("/update")
    public void updateUserItf(
        @RequestBody UserItfUpdateReq userItfUpdateReq) {
        if (userItfUpdateReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        UserItf userItf = new UserItf();
        BeanUtils.copyProperties(userItfUpdateReq, userItf);
        userItfService.validUserItf(userItf, false);

        UserItf oldUserItf = userItfService.getById(userItf.getId());
        if (oldUserItf == null) {
            throw new BusinessException(ResultCode.NULL_ERR);
        }

        boolean b = userItfService.updateById(userItf);
        if (!b) {
            throw new BusinessException(ResultCode.UPDATE_ERR);
        }
    }

    @SaCheckRole("admin")
    @GetMapping("/get/{id}")
    public UserItfVO getUserItfById(@PathVariable("id") Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        UserItf userItf = userItfService.getById(id);
        return UserItfVOConverter.INSTANCE.toUserItfVO(userItf);
    }

    @SaCheckRole("admin")
    @PostMapping("/list")
    public List<UserItfVO> listUserItf(
        @RequestBody UserItfQueryReq userItfQueryReq) {
        UserItf userItfQuery = new UserItf();
        if (userItfQueryReq != null) {
            BeanUtils.copyProperties(userItfQueryReq, userItfQuery);
        }

        QueryWrapper<UserItf> wrapper = new QueryWrapper<>(userItfQuery);
        List<UserItf> userItfList = userItfService.list(wrapper);
        return userItfList.stream()
            .map(UserItfVOConverter.INSTANCE::toUserItfVO)
            .collect(Collectors.toList());
    }

    @SaCheckRole("admin")
    @PostMapping("/list/page")
    public IPage<UserItfVO> listUserItfByPage(
        @RequestBody UserItfQueryReq userItfQueryReq) {
        if (userItfQueryReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        /*限制爬虫*/
        if (userItfQueryReq.getPageSize() > PageConstant.MAX_PAGE_SIZE) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        UserItf userItfQuery = new UserItf();
        BeanUtils.copyProperties(userItfQueryReq, userItfQuery);

        long pagenum = userItfQueryReq.getPageNum();
        long pagesize = userItfQueryReq.getPageSize();
        String sortField = userItfQueryReq.getSortField();
        boolean sortOrder = userItfQueryReq.isSortOrder();

        QueryWrapper<UserItf> wrapper = new QueryWrapper<>(userItfQuery);
        /*使用UserItfName字段做模糊查询*/
        wrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder, sortField);

        IPage<UserItf> userItfPage = userItfService.page(new Page<>(pagenum, pagesize), wrapper);

        return userItfPage.convert(UserItfVOConverter.INSTANCE::toUserItfVO);
    }
    //endregion

    @PostMapping("/check-invoke-auth")
    public Boolean checkInvokeAuth(@RequestBody InvokeAuthCheckReq invokeAuthCheckReq) {
        if (Objects.isNull(invokeAuthCheckReq)
            || !invokeAuthCheckReq.checkAllAttr()) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Long itfId = invokeAuthCheckReq.getItfId();
        Long userId = invokeAuthCheckReq.getUserId();

        return userItfService.checkInvokeAuth(itfId, userId);
    }

    @PutMapping("/count-increment")
    public void invokeCountIncrement(@RequestBody InvokeCountReq invokeCountReq) {
        if (Objects.isNull(invokeCountReq)
            || !invokeCountReq.checkAllAttr()) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }
        Long itfId = invokeCountReq.getItfId();
        Long userId = invokeCountReq.getUserId();

        userItfService.invokeCountIncrement(itfId, userId);
    }
}
