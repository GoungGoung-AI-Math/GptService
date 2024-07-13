package com.gonggong.gpt4j.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gonggong.gpt4j.consts.OpenAIModel;
import com.gonggong.gpt4j.dto.VisionReqDto;
import com.gonggong.gpt4j.config.openaiClient.ChatCompleteClient;
import com.gonggong.gpt4j.templete.chatMessage.res.Content;
import com.gonggong.gpt4j.templete.teachers.MathTeacherMessage;
import com.gonggong.gpt4j.templete.chatMessage.req.PromptMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatAIService {

    private final ChatCompleteClient chatCompleteClient;

    public String textCompletes(String prompt) {
        String body = "{\"model\": \"" + OpenAIModel.GPT_4O.toString() + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
        return chatCompleteClient.sendPostRequest(body);
    }

    public List<Content>visionComplete(VisionReqDto chatReqDto) {
        PromptMessage prompt = new PromptMessage(chatReqDto);
        log.info(prompt.toString());
//        String promptMessage = getPromptMessageJson(prompt);
        return chatCompleteClient.sendPostRequest(prompt);
    }

    public List<Content> visionMathQuery(VisionReqDto chatReqDto) {
        PromptMessage prompt = new PromptMessage(chatReqDto);
        prompt.setSystemPrompt(new MathTeacherMessage());
        log.info(prompt.toString());
//        String promptMessage = getPromptMessageJson(prompt);
        return chatCompleteClient.sendPostRequest(prompt);
    }

//    private static String getPromptMessageJson(PromptMessage prompt) {
//        String promptMessage;
//        try{
//            promptMessage = prompt.toJson();
//        } catch (JsonProcessingException e){
//            log.error(e.getMessage(), e);
//            throw new RuntimeException(e.getMessage(), e);
//        }
//        return promptMessage;
//    }
}
