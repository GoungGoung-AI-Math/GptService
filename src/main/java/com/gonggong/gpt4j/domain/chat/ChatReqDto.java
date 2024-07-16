package com.gonggong.gpt4j.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatReqDto {
    private String message;
    private Long userId;
}
