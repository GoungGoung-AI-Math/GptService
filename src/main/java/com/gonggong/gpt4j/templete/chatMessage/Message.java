package com.gonggong.gpt4j.templete.chatMessage;

import com.gonggong.gpt4j.templete.consts.Role;


public abstract class Message {
    abstract Role getRole();
    abstract String getValue();

    @Override
    public String toString(){
        return new StringBuilder().append("{\n role : ").append(getRole()).append('\n')
                .append("content : ").append(getValue()).append("\n }")
                .toString();
    }
}
