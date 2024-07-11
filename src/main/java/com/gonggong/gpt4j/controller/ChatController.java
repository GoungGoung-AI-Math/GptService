package com.gonggong.gpt4j.controller;

import com.gonggong.gpt4j.dto.ChatReqDto;
import com.gonggong.gpt4j.dto.PdfFileURLDto;
import com.gonggong.gpt4j.dto.VisionReqDto;
import com.gonggong.gpt4j.service.ChatAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatAIService chatAIService;

    @PostMapping("/text")
    public String getTextCompletions(@RequestBody ChatReqDto chatReqDto){

        return chatAIService.textCompletes(chatReqDto.getMessage());
    }

    @PostMapping("/vision")
    public String getVision(@RequestBody VisionReqDto chatReqDto){
        log.info(chatReqDto.toString());
        return chatAIService.visionComplete(chatReqDto);
    }

    @PostMapping("/math-teacher")
    public String getMathQuery(@RequestBody VisionReqDto chatReqDto){
        log.info(chatReqDto.toString());
        return chatAIService.visionMathQuery(chatReqDto);
    }

//    @PostMapping("/pdf-embedding")
//    public String savePdfEmbedding(@RequestBody PdfFileURLDto pdfFileURLDto){
//        chatAIService.savePdf2Embedding(pdfFileURLDto);
//        return null;
//    }
}
