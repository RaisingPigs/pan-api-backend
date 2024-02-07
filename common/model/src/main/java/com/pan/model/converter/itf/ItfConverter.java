package com.pan.model.converter.itf;

import com.pan.model.entity.Itf;
import com.pan.model.req.itf.ItfAddReq;
import com.pan.model.req.itf.ItfQueryReq;
import com.pan.model.req.itf.ItfUpdateReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-06 12:29
 **/
@Mapper
public interface ItfConverter {
    ItfConverter INSTANCE = Mappers.getMapper(ItfConverter.class);
    
    
    Itf toItf(ItfQueryReq itfQueryReq);
    Itf toItf(ItfUpdateReq itfUpdateReq);
    Itf toItf(ItfAddReq itfAddReq);
}
