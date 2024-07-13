package com.gonggong.gpt4j.templete.chatMessage.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gonggong.gpt4j.templete.consts.Usage;
import lombok.Getter;

import java.util.List;

// Define the main class
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteChatResponse {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Content> choices;
    private Usage usage;

    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

    @Override
    public String toString(){
        StringBuilder sb =  new StringBuilder()
                .append("{\n")
                .append("id : ").append(id).append(",\n")
                .append("object : ").append(object).append(",\n")
                .append("created : ").append(created).append(",\n")
                .append("model : ").append(model).append(",\n")
                .append("usage : ").append(usage).append(",\n");
        if(!choices.isEmpty()){
            sb.append("choices : [\n");
            for(Content c : choices){
                sb.append(c).append('\n');
            }
            sb.append(" ]\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
