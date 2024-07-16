package com.gonggong.gpt4j.domain.embedding.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.consts.OpenAIModel;
import com.gonggong.gpt4j.domain.VisionReqDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmbeddingMessage {
    @JsonProperty("model")
    private OpenAIModel model;
    @JsonProperty("input")
    protected String input;

    public EmbeddingMessage(String input){
        this.input = input;
        this.model = OpenAIModel.TEXT_EMBEDDING_SMALL;
    }

    public EmbeddingMessage(VisionReqDto reqDto){
        this.input = reqDto.getContents().get(0).getValue();
        this.model = OpenAIModel.TEXT_EMBEDDING_SMALL;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
