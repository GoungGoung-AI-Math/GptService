package com.gonggong.gpt4j.openaiClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.templete.chatMessage.CompleteChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Slf4j
@Component
@RequiredArgsConstructor
public class ChatCompleteClient{

    private final ApiKey API_KEY;

    private final java.lang.String CHAT_COMPLETE_URL= "https://api.openai.com/v1/chat/completions";

    public String sendPostRequest(String jsonRequest){
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = getHttpURLConnection(jsonRequest);

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
        CompleteChatResponse completeChatResponse = null;
        try {
            completeChatResponse = objectMapper.readValue(response.toString(), CompleteChatResponse.class);
            log.info(completeChatResponse.toString());
            // 필요한 다른 로직 추가
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return completeChatResponse.getChoices().get(0).getMessage().getValue();
    }

    private HttpURLConnection getHttpURLConnection( java.lang.String json) throws IOException {
        URL url = new URL(CHAT_COMPLETE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("POST");

        // Set headers
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");

        // Enable input and output streams
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // Write JSON data to request body
        OutputStream os = connection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
        return connection;
    }
}
