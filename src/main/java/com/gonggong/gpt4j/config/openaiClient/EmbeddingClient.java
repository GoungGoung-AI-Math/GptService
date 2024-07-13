package com.gonggong.gpt4j.config.openaiClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.config.ApiKey;
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
    private final String TEXT_EMBEDDING_URL= "https://api.openai.com/v1/embeddings";

    public EmbeddingResponse sendPostRequest(String json) {

        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = ClientConnection.getHttpURLConnection(json, TEXT_EMBEDDING_URL, API_KEY);

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            java.lang.String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            EmbeddingResponse embeddingResponse = objectMapper.readValue(response.toString(), EmbeddingResponse.class);
            log.info(embeddingResponse.toString());
            return embeddingResponse;
            // 필요한 다른 로직 추가
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
