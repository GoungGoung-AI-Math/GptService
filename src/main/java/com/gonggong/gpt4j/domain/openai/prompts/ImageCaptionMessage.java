package com.gonggong.gpt4j.domain.openai.prompts;

import com.gonggong.gpt4j.domain.chat.req.ReqMessage;
import com.gonggong.gpt4j.domain.chat.req.ReqTextContent;
import com.gonggong.gpt4j.consts.Role;

import java.util.List;

public class ImageCaptionMessage extends ReqMessage {
    public ImageCaptionMessage() {
        super(Role.SYSTEM, List.of(new ReqTextContent("Write a caption explaining the concepts and application methods needed to solve the given math problem. The caption should include the following information:\n" +
                "\t1.\tIdentify the type of problem. For example, explain whether it is a calculus problem, algebra problem, geometry problem, etc.\n" +
                "\t2.\tList and explain the key concepts needed to solve the problem. For example, differentiation, integration, equations, properties of triangles, etc.\n" +
                "\t3.\tSuggest application methods for solving the problem. For example, calculation methods using specific formulas, graph interpretation methods, etc.")));
    }
}
