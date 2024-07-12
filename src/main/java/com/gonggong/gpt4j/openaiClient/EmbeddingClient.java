package com.gonggong.gpt4j.openaiClient;

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

    public String sendPostRequest(String json) {

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



        return null;
    }
}
