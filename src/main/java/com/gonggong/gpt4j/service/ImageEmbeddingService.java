package com.gonggong.gpt4j.service;

import com.gonggong.gpt4j.config.openaiClient.ChatCompleteClient;
import com.gonggong.gpt4j.config.openaiClient.EmbeddingClient;
import com.gonggong.gpt4j.dto.VisionReqDto;
import com.gonggong.gpt4j.templete.chatMessage.req.PromptMessage;
import com.gonggong.gpt4j.templete.chatMessage.res.Content;
import com.gonggong.gpt4j.templete.consts.MessageType;
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
        PromptMessage prompt = new PromptMessage(reqDto, 300);
        prompt.setSystemPrompt(new ImageCaptionMessage());
        Content caption = chatCompleteClient.sendPostRequest(prompt).get(0);
        log.info(caption.toString());
        return caption;
    }

    public
}
