package com.gonggong.gpt4j.domain.caption;

import com.gonggong.gpt4j.domain.chat.ChatCompleteClient;
import com.gonggong.gpt4j.domain.embedding.EmbeddingClient;
import com.gonggong.gpt4j.domain.VisionReqDto;
import com.gonggong.gpt4j.domain.chat.req.PromptMessage;
import com.gonggong.gpt4j.domain.chat.res.Content;
import com.gonggong.gpt4j.domain.embedding.req.EmbeddingMessage;
import com.gonggong.gpt4j.domain.embedding.res.EmbeddingResponse;
import com.gonggong.gpt4j.domain.openai.prompts.ImageCaptionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageCaptionService {
    private final EmbeddingClient embeddingClient;
    private final ChatCompleteClient chatCompleteClient;
    private final ImageCaptionRepository imageCaptionRepository;
    public Content getImageCaption(VisionReqDto reqDto){
        PromptMessage prompt = new PromptMessage(reqDto, 250);
        prompt.setSystemPrompt(new ImageCaptionMessage());
        return chatCompleteClient.sendPostRequest(prompt).get(0);
    }

    private EmbeddingResponse getCaptionEmbedding(Content content){
        EmbeddingMessage embeddingMessage = new EmbeddingMessage(content.getMessage().getValue());
        return embeddingClient.sendPostRequest(embeddingMessage);
    }

    public ImageCaptionDto saveImageEmbedding(VisionReqDto reqDto){
        Content content = getImageCaption(reqDto);
        EmbeddingResponse embeddingResponse = getCaptionEmbedding(content);
        ImageCaptionVo imageCaptionVo = ImageCaptionVo.of(reqDto, content, embeddingResponse);
        ImageCaption imageCaption = imageCaptionRepository.save(ImageCaption.toEntity(imageCaptionVo));
        return ImageCaptionDto.toDto(imageCaption);
    }

    public List<ImageCaptionDto> getNearestCaptions(VisionReqDto reqDto){
        EmbeddingResponse embeddingResponse = embeddingClient.sendPostRequest(
                new EmbeddingMessage(reqDto));
        List<ImageCaption> imageCaptions = imageCaptionRepository.findTopNSimilarDocuments(
                embeddingResponse.getData().get(0).getEmbedding(), 3);

        imageCaptions.forEach(imageCaption ->
                log.info("name : {}, \n caption : {}",imageCaption.getName(),imageCaption.getCaption()));
        return imageCaptions.stream().map(ImageCaptionDto::toDto).collect(Collectors.toList());
    }
}
