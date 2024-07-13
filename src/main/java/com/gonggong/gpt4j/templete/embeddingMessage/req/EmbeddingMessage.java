package com.gonggong.gpt4j.templete.embeddingMessage.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.consts.OpenAIModel;
import com.gonggong.gpt4j.templete.chatMessage.req.ReqMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
