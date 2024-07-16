package com.gonggong.gpt4j.domain.chat.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gonggong.gpt4j.domain.VisionReqDto;
import com.gonggong.gpt4j.consts.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqMessage {
    @JsonProperty("role")
    private Role role;

    @JsonProperty("content")
    private List<ReqContent> contents;

    public ReqMessage(VisionReqDto visionReqDto){
        this.role = Role.USER;
        this.contents = visionReqDto.getContents();
    }
    @Override
    public String toString() {
        return "ReqMessage{" +
                "role=" + role +
                ", content=" + contents +
                '}';
    }
}