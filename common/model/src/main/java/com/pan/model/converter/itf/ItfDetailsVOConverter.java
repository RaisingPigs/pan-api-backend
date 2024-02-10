package com.pan.model.converter.itf;

import com.pan.model.converter.useritf.UserItfVOConverter;
import com.pan.model.entity.Itf;
import com.pan.model.entity.UserItf;
import com.pan.model.vo.itf.ItfDetailsVO;
import com.pan.model.vo.param.ParamVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-07 09:38
 **/
@Mapper(uses = {ItfVOConverter.class, UserItfVOConverter.class})
public interface ItfDetailsVOConverter {
    ItfDetailsVOConverter INSTANCE = Mappers.getMapper(ItfDetailsVOConverter.class);
    
    @Mapping(source = "itf", target = "itfVO")
    @Mapping(source = "userItf", target = "userItfVO")
    ItfDetailsVO toItfDetailsVO(
        Itf itf,
        UserItf userItf,
        List<ParamVO> queryParam,
        List<ParamVO> bodyParam,
        List<ParamVO> commonResp,
        List<ParamVO> respData
    );
}
