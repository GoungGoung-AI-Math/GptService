package com.gonggong.gpt4j.domain.chat.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.consts.OpenAIModel;
import com.gonggong.gpt4j.domain.VisionReqDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PromptMessage {
    @JsonProperty("model")
    private OpenAIModel model;
    @JsonProperty("messages")
    protected List<ReqMessage> messages;
    @JsonProperty("max_tokens")
    private int maxTokens;
    public PromptMessage (VisionReqDto dto){
        this.model = OpenAIModel.GPT_4O;
        this.messages = new ArrayList<>();
        this.maxTokens = 500;
        messages.add(new ReqMessage(dto));
    }

    public PromptMessage (VisionReqDto dto, int maxTokens){
        this.model = OpenAIModel.GPT_4O;
        this.messages = new ArrayList<>();
        this.maxTokens = maxTokens;
        messages.add(new ReqMessage(dto));
    }

    public void setSystemPrompt(ReqMessage systemMessage){
        messages.add(systemMessage);
    }
    @Override
    public String toString() {
        return "PromptMessage{" +
                "model=" + model +
                ", messages=" + messages +
                ", maxTokens=" + maxTokens +
                '}';
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
