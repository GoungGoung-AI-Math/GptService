package com.gonggong.gpt4j.consts;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OpenAIModel {
    TEXT_EMBEDDING_SMALL("text-embedding-3-small"),
    GPT_4O("gpt-4o"),
    GPT_4_TURBO("gpt-4-turbo"),
    GPT_3_5_TURBO("gpt-3.5-turbo");

    private String name;

    OpenAIModel(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName(){
        return name;
    }
}
