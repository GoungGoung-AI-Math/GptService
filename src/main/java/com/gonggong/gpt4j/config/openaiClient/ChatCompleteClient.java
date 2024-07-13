package com.gonggong.gpt4j.config.openaiClient;

import com.gonggong.gpt4j.config.ApiKey;
import com.gonggong.gpt4j.templete.chatMessage.req.PromptMessage;
import com.gonggong.gpt4j.templete.chatMessage.res.CompleteChatResponse;
import com.gonggong.gpt4j.templete.chatMessage.res.Content;
import com.gonggong.gpt4j.templete.consts.AIServicePath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ChatCompleteClient{

    private final ApiKey API_KEY;

    public List<Content> sendPostRequest(PromptMessage jsonRequest){
        try {
            RestTemplateClient<CompleteChatResponse, PromptMessage> restTemplateClient = new RestTemplateClient<>();
            CompleteChatResponse response = restTemplateClient.postWithBody(jsonRequest,
                    AIServicePath.CHAT_COMPLETE_PAHT, API_KEY, CompleteChatResponse.class);
            log.info("response : {}",response);
            return response.getChoices();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String sendPostRequest(String jsonRequest){
        try {
            RestTemplateClient<CompleteChatResponse, String> restTemplateClient = new RestTemplateClient<>();
            CompleteChatResponse response = restTemplateClient.postWithBody(jsonRequest,
                    AIServicePath.CHAT_COMPLETE_PAHT, API_KEY, CompleteChatResponse.class);
            log.info("response : {}",response);
            return response.getChoices().get(0).getMessage().getValue();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
