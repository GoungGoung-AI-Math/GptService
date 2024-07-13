package com.gonggong.gpt4j.config.openaiClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.config.ApiKey;
import com.gonggong.gpt4j.templete.chatMessage.req.PromptMessage;
import com.gonggong.gpt4j.templete.chatMessage.res.CompleteChatResponse;
import com.gonggong.gpt4j.templete.consts.AIServicePath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


@Slf4j
@Component
@RequiredArgsConstructor
public class ChatCompleteClient{

    private final ApiKey API_KEY;

    public String sendPostRequest(PromptMessage jsonRequest){
        try {
//            HttpURLConnection connection = ClientConnection.getHttpURLConnection(jsonRequest, CHAT_COMPLETE_URL, API_KEY);

            // Read response
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            java.lang.String line;
//            while ((line = br.readLine()) != null) {
//                response.append(line);
//            }
//            br.close();
            RestClient<CompleteChatResponse, PromptMessage> restClient = new RestClient<>();
            CompleteChatResponse response = restClient.postWithBody(jsonRequest,
                    AIServicePath.CHAT_COMPLETE_PAHT, API_KEY, CompleteChatResponse.class);
            log.info("response : {}",response);
            return response.getChoices().get(0).getMessage().getValue();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String sendPostRequest(String jsonRequest){
        try {
//            HttpURLConnection connection = ClientConnection.getHttpURLConnection(jsonRequest, CHAT_COMPLETE_URL, API_KEY);

            // Read response
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            java.lang.String line;
//            while ((line = br.readLine()) != null) {
//                response.append(line);
//            }
//            br.close();
            RestClient<CompleteChatResponse, String> restClient = new RestClient<>();
            CompleteChatResponse response = restClient.postWithBody(jsonRequest,
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
