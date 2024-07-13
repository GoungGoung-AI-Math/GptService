package com.gonggong.gpt4j.templete.chatMessage.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gonggong.gpt4j.templete.consts.MessageType;
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
    MessageType getType() {
        return type;
    }

    @Override
    String getValue() {
        return text;
    }
}
