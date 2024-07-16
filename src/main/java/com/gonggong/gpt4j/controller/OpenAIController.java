package com.gonggong.gpt4j.controller;

import com.gonggong.gpt4j.domain.caption.data.ImageCaptionDto;
import com.gonggong.gpt4j.domain.chat.ChatReqDto;
import com.gonggong.gpt4j.domain.embedding.PdfFileURLDto;
import com.gonggong.gpt4j.domain.VisionReqDto;

import com.gonggong.gpt4j.domain.chat.ChatAIService;
import com.gonggong.gpt4j.domain.caption.ImageCaptionService;
import com.gonggong.gpt4j.domain.embedding.TextEmbeddingService;
import com.gonggong.gpt4j.domain.chat.res.Content;
import com.gonggong.gpt4j.domain.embedding.res.EmbeddingResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final ImageCaptionService imageCaptionService;

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
    public ImageCaptionDto getImageCaption(@RequestBody VisionReqDto chatReqDto){
        return imageCaptionService.saveImageEmbedding(chatReqDto);
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

    @PostMapping("/image-captions")
    public List<ImageCaptionDto> saveImageEmbeddings(@RequestBody VisionReqDto reqDto){
        return imageCaptionService.saveImageEmbeddings(reqDto);
    }

    @GetMapping("/near-problems")
    public List<ImageCaptionDto> getNearestProblems(@RequestBody VisionReqDto reqDto){
        return imageCaptionService.getNearestCaptions(reqDto);
    }


}
