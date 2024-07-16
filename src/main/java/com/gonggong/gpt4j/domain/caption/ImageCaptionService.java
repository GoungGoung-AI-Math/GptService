package com.gonggong.gpt4j.domain.caption;

import com.gonggong.gpt4j.domain.caption.data.ImageCaption;
import com.gonggong.gpt4j.domain.caption.data.ImageCaptionDto;
import com.gonggong.gpt4j.domain.caption.data.ImageCaptionVo;
import com.gonggong.gpt4j.domain.caption.repository.ImageCaptionRepository;
import com.gonggong.gpt4j.domain.chat.ChatCompleteTemplate;
import com.gonggong.gpt4j.domain.embedding.EmbeddingTemplate;
import com.gonggong.gpt4j.domain.VisionReqDto;
import com.gonggong.gpt4j.domain.chat.req.PromptMessage;
import com.gonggong.gpt4j.domain.chat.res.Content;
import com.gonggong.gpt4j.domain.embedding.req.EmbeddingMessage;
import com.gonggong.gpt4j.domain.embedding.res.EmbeddingResponse;
import com.pgvector.PGvector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageCaptionService {
    private final EmbeddingTemplate embeddingTemplate;
    private final ChatCompleteTemplate chatCompleteTemplate;
    private final ImageCaptionRepository imageCaptionRepository;
    public Content getImageCaption(VisionReqDto reqDto){
        PromptMessage prompt = new PromptMessage(reqDto, 250);
        prompt.setSystemPrompt(new ImageCaptionPrompt());
        return chatCompleteTemplate.sendPostRequest(prompt).get(0);
    }

    public ImageCaptionDto saveImageEmbedding(VisionReqDto reqDto){
        Content content = getImageCaption(reqDto);
        EmbeddingResponse embeddingResponse = getCaptionEmbedding(content);
        ImageCaptionVo imageCaptionVo = ImageCaptionVo.of(reqDto, content, embeddingResponse);
        ImageCaption imageCaption = imageCaptionRepository.save(ImageCaption.toEntity(imageCaptionVo));
        return ImageCaptionDto.toDto(imageCaption);
    }

    public List<ImageCaptionDto> saveImageEmbeddings(VisionReqDto reqDto){
        List<VisionReqDto> visionReqDtos = splitVisionReqDto(reqDto);
        return visionReqDtos.parallelStream().map(this::saveImageEmbedding)
                .collect(Collectors.toList());
    }


    public List<ImageCaptionDto> getNearestCaptions(VisionReqDto reqDto){
        EmbeddingResponse embeddingResponse = embeddingTemplate.sendPostRequest(
                new EmbeddingMessage(reqDto));
        // List<Double> to Double[] conversion
        List<Double> embeddingList = embeddingResponse.getData().get(0).getEmbedding();
        PGvector vector= new PGvector(embeddingList); //.toArray();
        log.info("array : {}",vector);
        List<ImageCaption> imageCaptions = imageCaptionRepository.findTopNSimilarDocuments(
                vector, 3);

        imageCaptions.forEach(imageCaption ->
                log.info("name : {}, \n caption : {}",imageCaption.getName(),imageCaption.getCaption()));
        return imageCaptions.stream().map(ImageCaptionDto::toDto).collect(Collectors.toList());
    }


    private EmbeddingResponse getCaptionEmbedding(Content content){
        EmbeddingMessage embeddingMessage = new EmbeddingMessage(content.getMessage().getValue());
        return embeddingTemplate.sendPostRequest(embeddingMessage);
    }

    private List<VisionReqDto> splitVisionReqDto(VisionReqDto reqDto) {
        return reqDto.getContents().stream()
                .map(VisionReqDto::new)
                .collect(Collectors.toList());
    }
}
