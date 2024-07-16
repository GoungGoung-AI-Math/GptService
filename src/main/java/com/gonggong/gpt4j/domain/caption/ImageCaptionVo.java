package com.gonggong.gpt4j.domain.caption;

import com.gonggong.gpt4j.domain.VisionReqDto;
import com.gonggong.gpt4j.domain.chat.res.Content;
import com.gonggong.gpt4j.domain.embedding.res.EmbeddingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.awt.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ImageCaptionVo {
    private String name;
    private String caption;
    private List<Double> embedding;

    public static ImageCaptionVo of(VisionReqDto reqDto, Content content, EmbeddingResponse embeddingResponse){
        return ImageCaptionVo.builder()
                .name(reqDto.getContents().get(0).getValue())
                .caption(content.getMessage().getValue())
                .embedding(embeddingResponse.getData().get(0).getEmbedding())
                .build();
    }
}
