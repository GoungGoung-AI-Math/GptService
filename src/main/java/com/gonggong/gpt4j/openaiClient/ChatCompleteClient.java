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

    private final String CHAT_COMPLETE_URL= "https://api.openai.com/v1/chat/completions";

    public String sendPostRequest(String jsonRequest){
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = ClientConnection.getHttpURLConnection(jsonRequest, CHAT_COMPLETE_URL, API_KEY);

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
}
