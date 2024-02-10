package com.pan.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pan.model.entity.UserItf;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr.Pan
 * @description 针对表【user_interface_info(用户接口调用表)】的数据库操作Mapper
 * @createDate 2023-03-01 09:46:46
 * @Entity com.pan.app.model.entity.UserInterfaceInfo
 */
public interface UserItfMapper extends BaseMapper<UserItf> {
    int itfInvokeCountIncrement(@Param("itfId") long itfId, @Param("userId") long userId);

    void leftCountIncrementById(@Param("id") long id, @Param("count") int count);

    void leftCountIncrement(@Param("itfId") long itfId, @Param("userId") long userId, @Param("count") int count);
}




