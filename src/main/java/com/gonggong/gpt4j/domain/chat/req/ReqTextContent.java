package com.gonggong.gpt4j.domain.chat.req;

import com.example.demo.my.kafka.infra.kafka.dtos.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("text")
public class ReqTextContent extends ReqContent {

    private final MessageType type = MessageType.TEXT;

    @JsonProperty("text")
    private String text;

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return text;
    }
}
