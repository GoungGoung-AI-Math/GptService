package com.gonggong.gpt4j.config.openaiClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.config.ApiKey;
import com.gonggong.gpt4j.templete.chatMessage.req.PromptMessage;
import com.gonggong.gpt4j.templete.chatMessage.res.CompleteChatResponse;
import com.gonggong.gpt4j.templete.consts.AIServicePath;
import com.gonggong.gpt4j.templete.embeddingMessage.req.EmbeddingMessage;
import com.gonggong.gpt4j.templete.embeddingMessage.res.EmbeddingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmbeddingClient{

    private final ApiKey API_KEY;

    public EmbeddingResponse sendPostRequest(EmbeddingMessage jsonRequest) {

        try {
            RestTemplateClient<EmbeddingResponse, EmbeddingMessage> restTemplateClient = new RestTemplateClient<>();
            EmbeddingResponse response = restTemplateClient.postWithBody(jsonRequest,
                    AIServicePath.EMBEDDING_PATH, API_KEY, EmbeddingResponse.class);
            return response;
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
