package com.gonggong.gpt4j.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gonggong.gpt4j.consts.OpenAIModel;
import com.gonggong.gpt4j.dto.VisionReqDto;
import com.gonggong.gpt4j.openaiClient.ChatCompleteClient;
import com.gonggong.gpt4j.templete.mathteacher.MathTeacherMessage;
import com.gonggong.gpt4j.templete.promptMessage.PromptMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatAIService {

    private final ChatCompleteClient chatCompleteClient;

    public String textCompletes(java.lang.String prompt) {
        String body = "{\"model\": \"" + OpenAIModel.GPT_4O + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
        return chatCompleteClient.sendPostRequest(body);
    }

    public String visionComplete(VisionReqDto chatReqDto) {
        PromptMessage prompt = new PromptMessage(chatReqDto);
        log.info(prompt.toString());
        String promptMessage = getPromptMessageJson(prompt);
        return chatCompleteClient.sendPostRequest(promptMessage);
    }

    public String visionMathQuery(VisionReqDto chatReqDto) {
        PromptMessage prompt = new PromptMessage(chatReqDto);
        prompt.setSystemPrompt(new MathTeacherMessage());
        log.info(prompt.toString());
        String promptMessage = getPromptMessageJson(prompt);
        return chatCompleteClient.sendPostRequest(promptMessage);
    }


    private static String getPromptMessageJson(PromptMessage prompt) {
        java.lang.String promptMessage;
        try{
            promptMessage = prompt.toJson();
        } catch (JsonProcessingException e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return promptMessage;
    }
}
