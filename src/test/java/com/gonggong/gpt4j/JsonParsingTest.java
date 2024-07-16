package com.gonggong.gpt4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggong.gpt4j.domain.chat.res.CompleteChatResponse;
import org.junit.jupiter.api.Test;

public class JsonParsingTest {
    @Test
    public void jsonParseTextResponse(){
        java.lang.String jsonString = "{  \"id\": \"chatcmpl-9jHYc3FilCnmd22CtgEht3jNtyTJP\",  \"object\": \"chat.completion\",  \"created\": 1720579278,  \"model\": \"gpt-4o-2024-05-13\",  \"choices\": [    {      \"index\": 0,      \"message\": {        \"role\": \"assistant\",        \"content\": \"커피의 기원에 대해 알려드리겠습니다.\\n\\n커피는 약 9세기 아프리카 에티오피아에서 처음 발견된 것으로 전해집니다. 전설에 따르면, 칼디라는 이름의 염소 목동이 그의 염소들이 어떤 붉은 열매를 먹고 나서 매우 활기차게 움직이는 것을 발견했습니다. 이 열매가 커피 열매였고, 그는 이 열매를 지역 수도원에 있는 수도사에게 가져갔습니다. 수도사들은 이 열매를 섭취한 후 기도할 때 졸음을 이겨낼 수 있는 것을 확인하고, 커피 열매를 이용한 음료를 만들기 시작했습니다. 이렇게 커피는 점차 사람들에게 알려지기 시작했습니다.\\n\\n커피는 이후 아라비아 반도로 전해졌고, 15세기에는 예멘의 수피 수도사들이 커피를 마시기 시작했습니다. 16세기에는 이슬람 세계의 중요한 상업 제품으로 자리 잡았습니다. 커피는 오스만 제국을 거쳐 유럽으로 전파되었고, 유럽에서 커피하우스가 성행하게 되면서 세계 전역으로 퍼지게 되었습니다.\\n\\n아쉽게도, 현재 사진을 직접 보여드릴 수는 없습니다. 하지만 커피 열매나 커피 음료의 사진을 검색하여 쉽게 찾아보실 수 있습니다. 구체적으로 온라인 이미지 검색 도구를 사용해서 원하는 크기의 사진을 찾으실 수 있을 것입니다.\"      },      \"logprobs\": null,      \"finish_reason\": \"stop\"    }  ],  \"usage\": {    \"prompt_tokens\": 33,    \"completion_tokens\": 330,    \"total_tokens\": 363  },  \"system_fingerprint\": \"fp_d576307f90\"}";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CompleteChatResponse response = objectMapper.readValue(jsonString, CompleteChatResponse.class);
            System.out.println(response);
            // 출력: Usage{promptTokens=33, completionTokens=330, totalTokens=363}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsonParseUserRequest(){
//        CompleteChatResponse prompt = "커피의 기원에 대해 10글자로 알려줘";
//
//        VisionReqDto dto = new VisionReqDto(List.of(
//                new ReqTextContent(MessageType.TEXT,"사진이 어떤 풍경을 담고 있는지 알려줘"),
//                new ReqImageContent(MessageType.IMAGE_URL, new EncodedImage("https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Gfp-wisconsin-madison-the-nature-boardwalk.jpg/2560px-Gfp-wisconsin-madison-the-nature-boardwalk.jpg"))));
//        // PromptMessage 객체 생성
//        PromptMessage promptMessage = new PromptMessage(dto);
//
//        // ObjectMapper 인스턴스 생성
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            // 객체를 JSON 문자열로 직렬화
//            CompleteChatResponse jsonString = objectMapper.writeValueAsString(promptMessage);
//            System.out.println(jsonString);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

}
