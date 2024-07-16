package com.gonggong.gpt4j.domain.embedding;

import com.gonggong.gpt4j.domain.embedding.req.EmbeddingMessage;
import com.gonggong.gpt4j.domain.embedding.res.EmbeddingResponse;
import com.gonggong.gpt4j.consts.AIServicePath;
import com.gonggong.gpt4j.domain.openai.gptClient.ApiKey;
import com.gonggong.gpt4j.domain.openai.gptClient.RestTemplateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmbeddingClient{

    private final ApiKey API_KEY;

    public EmbeddingResponse sendPostRequest(EmbeddingMessage jsonRequest) {

        try {
            RestTemplateClient<EmbeddingResponse, EmbeddingMessage> restTemplateClient = new RestTemplateClient<>();
            return restTemplateClient.postWithBody(jsonRequest,
                    AIServicePath.EMBEDDING_PATH, API_KEY, EmbeddingResponse.class);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
