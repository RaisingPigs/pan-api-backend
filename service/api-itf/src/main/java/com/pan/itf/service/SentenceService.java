package com.pan.itf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pan.itf.model.entity.Sentence;
import com.pan.itf.model.enums.sentence.TypeEnum;

/**
 * @author Mr.Pan
 * @description 针对表【sentence(句子表)】的数据库操作Service
 * @createDate 2024-02-14 15:48:54
 */
public interface SentenceService extends IService<Sentence> {
    String randomSentenceContent(TypeEnum typeEnum);
}
