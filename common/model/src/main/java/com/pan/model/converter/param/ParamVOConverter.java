package com.pan.model.converter.param;

import com.pan.model.entity.Param;
import com.pan.model.vo.param.ParamVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-07 09:24
 **/
@Mapper
public interface ParamVOConverter {
    ParamVOConverter INSTANCE = Mappers.getMapper(ParamVOConverter.class);

    ParamVO toParamVo(Param param);
}
