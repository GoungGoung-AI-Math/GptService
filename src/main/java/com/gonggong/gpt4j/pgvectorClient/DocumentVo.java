package com.gonggong.gpt4j.pgvectorClient;

import com.gonggong.gpt4j.dto.VisionReqDto;
import com.gonggong.gpt4j.templete.chatMessage.res.Content;
import com.gonggong.gpt4j.templete.embeddingMessage.res.EmbeddingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DocumentVo {
    private String name;
    private String caption;
    private Double[] embedding;

    public static DocumentVo of(VisionReqDto reqDto, Content content, EmbeddingResponse embeddingResponse){
        return DocumentVo.builder()
                .name(reqDto.getContents().get(0).getValue())
                .caption(content.getMessage().getValue())
                .embedding((Double[]) embeddingResponse.getData().toArray())
                .build();
    }
}
