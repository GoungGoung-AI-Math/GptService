package com.gonggong.gpt4j.service;

import com.gonggong.gpt4j.clients.openaiClient.ChatCompleteClient;
import com.gonggong.gpt4j.clients.openaiClient.EmbeddingClient;
import com.gonggong.gpt4j.dto.VisionReqDto;
import com.gonggong.gpt4j.templete.chatMessage.req.PromptMessage;
import com.gonggong.gpt4j.templete.chatMessage.res.Content;
import com.gonggong.gpt4j.templete.embeddingMessage.req.EmbeddingMessage;
import com.gonggong.gpt4j.templete.embeddingMessage.res.EmbeddingResponse;
import com.gonggong.gpt4j.templete.prompts.ImageCaptionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageEmbeddingService {
    private final EmbeddingClient embeddingClient;
    private final ChatCompleteClient chatCompleteClient;
    public Content getImageCaption(VisionReqDto reqDto){
        PromptMessage prompt = new PromptMessage(reqDto, 250);
        prompt.setSystemPrompt(new ImageCaptionMessage());
        return chatCompleteClient.sendPostRequest(prompt).get(0);
    }

    public EmbeddingResponse getCaptionEmbedding(Content content){
        EmbeddingMessage embeddingMessage = new EmbeddingMessage(content.getMessage().getValue());
        EmbeddingResponse response = embeddingClient.sendPostRequest(embeddingMessage);
        return response;
    }

}
