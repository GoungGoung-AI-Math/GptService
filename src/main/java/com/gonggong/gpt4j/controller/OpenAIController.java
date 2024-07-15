package com.gonggong.gpt4j.controller;

import com.gonggong.gpt4j.dto.ChatReqDto;
import com.gonggong.gpt4j.dto.PdfFileURLDto;
import com.gonggong.gpt4j.dto.VisionReqDto;
import com.gonggong.gpt4j.service.ChatAIService;
import com.gonggong.gpt4j.service.ImageEmbeddingService;
import com.gonggong.gpt4j.service.TextEmbeddingService;
import com.gonggong.gpt4j.templete.chatMessage.res.Content;
import com.gonggong.gpt4j.templete.embeddingMessage.res.EmbeddingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OpenAIController {

    private final ChatAIService chatAIService;
    private final TextEmbeddingService textEmbeddingService;
    private final ImageEmbeddingService imageEmbeddingService;

    @PostMapping("/text")
    public String getTextCompletions(@RequestBody ChatReqDto chatReqDto){

        return chatAIService.textCompletes(chatReqDto.getMessage());
    }

    @PostMapping("/vision")
    public Content getVision(@RequestBody VisionReqDto chatReqDto){
        log.info(chatReqDto.toString());
        return chatAIService.visionComplete(chatReqDto);
    }

    @PostMapping("/math-teacher")
    public Content getMathQuery(@RequestBody VisionReqDto chatReqDto){
        log.info(chatReqDto.toString());
        return chatAIService.visionMathQuery(chatReqDto);
    }

    @PostMapping("/image-caption")
    public Content getImageCaption(@RequestBody VisionReqDto chatReqDto){
        return imageEmbeddingService.getImageCaption(chatReqDto);
    }

    @PostMapping("/simpleEmbedding")
    public EmbeddingResponse getSimpleEmbedding(@RequestBody ChatReqDto chatReqDto){
        return textEmbeddingService.embeddingSimpleText(chatReqDto.getMessage());
    }

    @PostMapping("/embeddingSearch")
    public EmbeddingResponse getEmbeddingSimilarityText(@RequestBody ChatReqDto chatReqDto){
        return textEmbeddingService.embeddingSimpleText(chatReqDto.getMessage());
    }

    @PostMapping("/pdf-embedding")
    public List<EmbeddingResponse> savePdfEmbedding(@RequestBody PdfFileURLDto pdfFileURLDto){
        return textEmbeddingService.getEmbeddingObjectFromS3(pdfFileURLDto);
    }


}
