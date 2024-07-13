package com.gonggong.gpt4j.templete.chatMessage.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gonggong.gpt4j.templete.consts.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageMessage extends Message {

    private Role role;
    @JsonProperty("url")
    private String imageUrl;

    @Override
    public Role getRole() {
        return role;
    }

    // Getters and setters
    @Override
    public String getValue() {
        return imageUrl;
    }
}
