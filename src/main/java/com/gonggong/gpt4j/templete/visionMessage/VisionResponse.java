package com.gonggong.gpt4j.templete.visionMessage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gonggong.gpt4j.templete.chatMessage.Content;
import lombok.Getter;

import java.util.List;

// Define the main class
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisionResponse {
    private String role;
    private String object;
    private Long created;
    private String model;
    private List<Content> content;
}
