package com.gonggong.gpt4j.dto;

import com.gonggong.gpt4j.templete.chatMessage.req.EncodedImage;
import com.gonggong.gpt4j.templete.chatMessage.req.ReqContent;

import com.gonggong.gpt4j.templete.chatMessage.req.ReqImageContent;
import com.gonggong.gpt4j.templete.chatMessage.req.ReqTextContent;
import com.gonggong.gpt4j.templete.consts.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisionReqDto {
    private List<ReqContent> contents;

//    public VisionReqDto(MessageType type, String content){
//        this.contents = new ArrayList<>();
//        if(MessageType.TEXT.equals(type)){
//            this.contents.add(new ReqTextContent(content));
//        } else{
//            this.contents.add(new ReqImageContent(new EncodedImage(content)));
//        }
//    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(ReqContent reqContent : contents){
            sb.append(reqContent).append("\n");
        }
        return sb.toString();
    }
}
