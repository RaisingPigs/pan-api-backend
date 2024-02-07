package com.pan.model.converter.itf;

import com.pan.model.entity.Itf;
import com.pan.model.vo.itf.ItfVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItfVOConverter {
    ItfVOConverter INSTANCE = Mappers.getMapper(ItfVOConverter.class);

    ItfVO toItfVo(Itf itf);
}