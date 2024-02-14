package com.pan.itf.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pan.itf.mapper.SentenceMapper;
import com.pan.itf.model.entity.Sentence;
import com.pan.itf.model.enums.sentence.TypeEnum;
import com.pan.itf.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Pan
 * @description 针对表【sentence(句子表)】的数据库操作Service实现
 * @createDate 2024-02-14 15:48:54
 */
@Service
@RequiredArgsConstructor
public class SentenceServiceImpl
    extends ServiceImpl<SentenceMapper, Sentence>
    implements SentenceService {
    private final SentenceMapper sentenceMapper;

    @Override
    public String randomSentenceContent(TypeEnum typeEnum) {
        long count = lambdaQuery()
            .eq(Sentence::getType, typeEnum.getCode())
            .count();
        int offset = RandomUtil.randomInt((int) count);
        return sentenceMapper.getContentFromOffset(typeEnum.getCode(), offset);
    }
}




