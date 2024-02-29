package com.pan.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pan.admin.service.UserService;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.common.resp.ResultUtils;
import com.pan.common.util.AuthUtils;
import com.pan.model.constant.PageConstant;
import com.pan.model.converter.user.UserAkSkVOConverter;
import com.pan.model.converter.user.UserConverter;
import com.pan.model.converter.user.UserVOConverter;
import com.pan.model.entity.User;
import com.pan.model.req.user.AccessKeyReq;
import com.pan.model.req.user.UserAddReq;
import com.pan.model.req.user.UserQueryReq;
import com.pan.model.req.user.UserUpdateReq;
import com.pan.model.vo.user.UserAkSkVO;
import com.pan.model.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 16:48
 **/
@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    //region 增删改查

    @PostMapping("/add")
    public BaseResponse<Long> addUser(
        @RequestBody UserAddReq userAddReq) {
        if (userAddReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        User user = UserConverter.INSTANCE.toUser(userAddReq);
        userService.validUser(user, true);

        boolean save = userService.addUser(user);
        if (!save) {
            throw new BusinessException(ResultCode.SAVE_ERR);
        }

        return ResultUtils.success(user.getId());
    }


    @DeleteMapping("/delete/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable("id") Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        User oldUser = userService.getById(id);
        if (oldUser == null) {
            throw new BusinessException(ResultCode.NULL_ERR);
        }

        boolean b = userService.removeById(id);
        if (!b) {
            throw new BusinessException(ResultCode.DELETE_ERR);
        }

        return ResultUtils.success();
    }


    @PutMapping("/update")
    public BaseResponse<Void> updateUser(
        @RequestBody UserUpdateReq userUpdateReq) {
        if (userUpdateReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        User user = UserConverter.INSTANCE.toUser(userUpdateReq);
        userService.validUser(user, false);

        User oldUser = userService.getById(user.getId());
        if (oldUser == null) {
            throw new BusinessException(ResultCode.NULL_ERR);
        }

        boolean b = userService.updateById(user);
        if (!b) {
            throw new BusinessException(ResultCode.UPDATE_ERR);
        }

        return ResultUtils.success();
    }


    @GetMapping("/get/{id}")
    public BaseResponse<UserVO> getUserById(
        @PathVariable("id")
        @NotNull(message = "参数异常")
        @Min(value = 1, message = "id不正确")
        Long id) {

        User user = userService.getById(id);
        UserVO userVO = UserVOConverter.INSTANCE.toUserVO(user);
        return ResultUtils.success(userVO);
    }


    @PostMapping("/list")
    public BaseResponse<List<UserVO>> listUser(
        @RequestBody UserQueryReq userQueryReq) {
        User user = null;
        if (userQueryReq != null) {
            user = UserConverter.INSTANCE.toUser(userQueryReq);
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        List<User> userList = userService.list(wrapper);

        List<UserVO> userVOList = userList.stream().map(UserVOConverter.INSTANCE::toUserVO).collect(Collectors.toList());
        return ResultUtils.success(userVOList);
    }


    @PostMapping("/list/page")
    public BaseResponse<IPage<UserVO>> listUserByPage(
        @RequestBody UserQueryReq userQueryReq) {
        if (userQueryReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        /*限制爬虫*/
        if (userQueryReq.getPageSize() > PageConstant.MAX_PAGE_SIZE) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        User user = UserConverter.INSTANCE.toUser(userQueryReq);

        long pageNum = userQueryReq.getPageNum();
        long pageSize = userQueryReq.getPageSize();
        String sortField = userQueryReq.getSortField();
        boolean sortOrder = userQueryReq.isSortOrder();
        String name = user.getName();
        String username = user.getUsername();
        user.setName(null);
        user.setUsername(null);

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        /*使用userName字段做模糊查询*/
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.like(StringUtils.isNotBlank(username), "username", username);
        wrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder, sortField);

        IPage<User> userPage = userService.page(new Page<>(pageNum, pageSize), wrapper);
        IPage<UserVO> userVOPage = userPage.convert(UserVOConverter.INSTANCE::toUserVO);

        return ResultUtils.success(userVOPage);
    }
    //endregion

    @PostMapping("/user-by-ak")
    public User getUserByAccessKey(
        @RequestBody AccessKeyReq accessKeyReq) {
        String accessKey = accessKeyReq.getAccessKey();

        return userService.lambdaQuery()
            .eq(User::getAccessKey, accessKey)
            .one();
    }

    @PostMapping("/sk-by-ak")
    public BaseResponse<String> getSecretKeyByAccessKey(
        @RequestBody AccessKeyReq accessKeyReq) {
        String accessKey = accessKeyReq.getAccessKey();

        String secretKey = userService.lambdaQuery()
            .eq(User::getAccessKey, accessKey)
            .one()
            .getSecretKey();

        return ResultUtils.success(secretKey);
    }

    @PostMapping("/ak-sk")
    public BaseResponse<UserAkSkVO> getUserWithAkSk() {
        Long id = AuthUtils.getLoginUserId();
        User user = userService.getById(id);

        if (user == null) {
            throw new BusinessException(ResultCode.NULL_ERR, "用户不存在");
        }

        UserAkSkVO userAkSkVo = UserAkSkVOConverter.INSTANCE.toUserAkSkVo(user);
        return ResultUtils.success(userAkSkVo);
    }
}
