package com.gonggong.gpt4j.domain.chat.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gonggong.gpt4j.consts.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TextMessage extends Message {
    @JsonProperty("role")
    private Role role;
    @JsonProperty("content")
    private String value;

    @Override
    public Role getRole() {
        return role;
    }

    // Getters and setters
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return new StringBuilder()
                .append("{role : ").append(role).append(", ")
                .append("content : ").append(value).append("}")
                .toString();
    }
}
