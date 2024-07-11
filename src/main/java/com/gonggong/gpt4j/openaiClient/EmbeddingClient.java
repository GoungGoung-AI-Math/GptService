package com.gonggong.gpt4j.openaiClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmbeddingClient{

    private final ApiKey API_KEY;
    private final java.lang.String CHAT_COMPLETE_URL= "https://api.openai.com/v1/embeddings";

    public String sendPostRequest(String json) {



        return null;
    }
}
