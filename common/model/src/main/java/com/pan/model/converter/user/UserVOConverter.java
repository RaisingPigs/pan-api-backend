package com.pan.model.converter.user;

import com.pan.model.dto.user.UserDTO;
import com.pan.model.entity.User;
import com.pan.model.vo.user.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-01 15:37
 **/
@Mapper
public interface UserVOConverter {
    UserVOConverter INSTANCE = Mappers.getMapper(UserVOConverter.class);

    UserVO toUserVO(User user);

    UserVO toUserVO(UserDTO userDTO);
}
