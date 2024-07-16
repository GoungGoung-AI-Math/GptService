package com.gonggong.gpt4j.consts;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    SYSTEM("system"),
    ASSISTANT("assistant"),
    USER("user");

    private String role;

    Role(String role){
        this.role = role;
    }

    @JsonValue
    public String getRole(){
        return role;
    }
}
