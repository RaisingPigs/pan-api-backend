package com.pan.model.converter.useritf;

import com.pan.model.entity.UserItf;
import com.pan.model.vo.useritf.UserItfVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserItfVOConverter {
    UserItfVOConverter INSTANCE = Mappers.getMapper(UserItfVOConverter.class);

    UserItfVO toUserItfVO(UserItf userItf);
}
