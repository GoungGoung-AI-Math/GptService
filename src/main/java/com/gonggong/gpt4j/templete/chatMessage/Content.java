package com.gonggong.gpt4j.templete.chatMessage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

// Define the Content class
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    private Integer index;
    private TextMessage message;
    @JsonProperty("finish_reason")
    private CompleteChatResponse finishReason;

    @Override
    public String toString(){
        return new StringBuilder().append("  { \n   index : ").append(index).append(", \n")
                .append("   message : ").append(message).append(", \n")
                .append("   finish_reason : ").append(finishReason).append("\n  }").toString();
    }
}
