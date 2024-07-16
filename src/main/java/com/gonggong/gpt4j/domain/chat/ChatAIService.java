package com.gonggong.gpt4j.domain.chat;

import com.gonggong.gpt4j.consts.OpenAIModel;
import com.gonggong.gpt4j.domain.VisionReqDto;
import com.gonggong.gpt4j.domain.chat.res.Content;
import com.gonggong.gpt4j.domain.chat.req.PromptMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatAIService {

    private final ChatCompleteTemplate chatCompleteTemplate;

    public String textCompletes(String prompt) {
        String body = "{\"model\": \"" + OpenAIModel.GPT_4O.toString() + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
        return chatCompleteTemplate.sendPostRequest(body);
    }

    public Content visionComplete(VisionReqDto chatReqDto) {
        PromptMessage prompt = new PromptMessage(chatReqDto);
        log.info(prompt.toString());
//        String promptMessage = getPromptMessageJson(prompt);
        return chatCompleteTemplate.sendPostRequest(prompt).get(0);
    }

    public Content visionMathQuery(VisionReqDto chatReqDto) {
        PromptMessage prompt = new PromptMessage(chatReqDto);
        prompt.setSystemPrompt(new MathTeacherPrompt());
        log.info(prompt.toString());
//        String promptMessage = getPromptMessageJson(prompt);
        return chatCompleteTemplate.sendPostRequest(prompt).get(0);
    }
}
