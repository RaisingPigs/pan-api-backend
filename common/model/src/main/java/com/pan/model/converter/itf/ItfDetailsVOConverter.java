package com.pan.model.converter.itf;

import com.pan.model.entity.Itf;
import com.pan.model.vo.itf.ItfDetailsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-07 09:38
 **/
@Mapper
public interface ItfDetailsVOConverter {
    ItfDetailsVOConverter INSTANCE = Mappers.getMapper(ItfDetailsVOConverter.class);

    ItfDetailsVO toItfDetailsVo(Itf itf);
}
