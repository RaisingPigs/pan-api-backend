package com.pan.itf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pan.itf.model.entity.Sentence;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr.Pan
 * @description 针对表【sentence(句子表)】的数据库操作Mapper
 * @createDate 2024-02-14 15:48:54
 * @Entity com.pan.itf.model.entity.Sentence
 */
public interface SentenceMapper extends BaseMapper<Sentence> {
    String getContentFromOffset(@Param("type") int type, @Param("offset") int offset);
}




