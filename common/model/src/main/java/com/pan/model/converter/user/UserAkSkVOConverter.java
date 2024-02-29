package com.pan.model.converter.user;

import com.pan.model.entity.User;
import com.pan.model.vo.user.UserAkSkVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-29 11:13
 **/
@Mapper
public interface UserAkSkVOConverter {
    UserAkSkVOConverter INSTANCE = Mappers.getMapper(UserAkSkVOConverter.class);

    UserAkSkVO toUserAkSkVo(User user);
}
