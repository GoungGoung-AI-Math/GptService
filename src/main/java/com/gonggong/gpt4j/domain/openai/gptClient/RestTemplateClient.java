package com.gonggong.gpt4j.domain.openai.gptClient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.consts.AIServicePath;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class RestTemplateClient<T, E>{
    public T postWithBody(E body, AIServicePath path, ApiKey apiKey, Class<T> responseType) throws JsonProcessingException {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openai.com")
                .path(path.toString())
                .encode()
                .build()
                .toUri();
        RequestEntity<E> requestEntity = RequestEntity
                .post(uri)
                .header("Authorization", "Bearer " + apiKey.getApiKey())
                .header("Content-Type", "application/json")
                .body(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 응답 본문을 String으로 받고, 이를 ObjectMapper를 사용하여 T 타입으로 역직렬화
        return objectMapper.readValue(responseEntity.getBody(), responseType);
    }
}
