package com.gonggong.gpt4j.service;

import com.gonggong.gpt4j.pgvectorClient.Document;
import com.gonggong.gpt4j.pgvectorClient.DocumentRepository;
import com.gonggong.gpt4j.pgvectorClient.DocumentVo;
import com.gonggong.gpt4j.templete.openaiClient.ChatCompleteClient;
import com.gonggong.gpt4j.templete.openaiClient.EmbeddingClient;
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
    private final DocumentRepository documentRepository;
    private Content getImageCaption(VisionReqDto reqDto){
        PromptMessage prompt = new PromptMessage(reqDto, 250);
        prompt.setSystemPrompt(new ImageCaptionMessage());
        return chatCompleteClient.sendPostRequest(prompt).get(0);
    }

    private EmbeddingResponse getCaptionEmbedding(Content content){
        EmbeddingMessage embeddingMessage = new EmbeddingMessage(content.getMessage().getValue());
        return embeddingClient.sendPostRequest(embeddingMessage);
    }

    public DocumentVo saveImageEmbedding(VisionReqDto reqDto){
        Content content = getImageCaption(reqDto);
        EmbeddingResponse embeddingResponse = getCaptionEmbedding(content);
        DocumentVo documentVo = DocumentVo.of(reqDto, content, embeddingResponse);
        documentRepository.save(Document.toEntity(documentVo));
        return documentVo;
    }

}
