package com.gonggong.gpt4j.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gonggong.gpt4j.config.openaiClient.EmbeddingClient;
import com.gonggong.gpt4j.templete.embeddingMessage.req.EmbeddingMessage;
import com.gonggong.gpt4j.templete.embeddingMessage.res.EmbeddingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmbeddingAIService {
    private final EmbeddingClient embeddingClient;

    public String embeddingSimpleText(String text){
        EmbeddingMessage embeddingMessage = new EmbeddingMessage(text);
        EmbeddingResponse response = embeddingClient.sendPostRequest(embeddingMessage);
        log.info(response.toString());
        return response.toString();
    }

    private static String getEmbeddingMessageJson(EmbeddingMessage embeddingMessage){
        String message;
        try{
            message = embeddingMessage.toJson();
        } catch (JsonProcessingException e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return message;
    }

}
