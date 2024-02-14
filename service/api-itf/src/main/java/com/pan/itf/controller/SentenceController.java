package com.pan.itf.controller;

import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultUtils;
import com.pan.itf.model.enums.sentence.TypeEnum;
import com.pan.itf.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-22 13:43
 **/
@RestController
@RequestMapping("/sentence")
@RequiredArgsConstructor
public class SentenceController {
    private final SentenceService sentenceService;

    @GetMapping("/chicken_soup")
    public BaseResponse<String> randomChickenSoup() {
        String content = sentenceService.randomSentenceContent(TypeEnum.CHICKEN_SOUP);

        return ResultUtils.success(content);
    }

    @GetMapping("/love_talk")
    public BaseResponse<String> randomLoveTalk() {
        String content = sentenceService.randomSentenceContent(TypeEnum.LOVE_TALK);

        return ResultUtils.success(content);
    }

    @GetMapping("/joke")
    public BaseResponse<String> randomJoke() {
        String content = sentenceService.randomSentenceContent(TypeEnum.JOKE);

        return ResultUtils.success(content);
    }
}
