package com.gonggong.gpt4j.domain.attempt;

import com.example.demo.my.kafka.infra.kafka.dtos.MessageType;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
import com.gonggong.gpt4j.domain.VisionReqDto;
import com.gonggong.gpt4j.domain.attempt.publisher.kafka.AttemptAnalysisResponsePublisher;
import com.gonggong.gpt4j.domain.chat.ChatAIService;
import com.gonggong.gpt4j.domain.chat.req.EncodedImage;
import com.gonggong.gpt4j.domain.chat.req.ReqContent;
import com.gonggong.gpt4j.domain.chat.req.ReqImageContent;
import com.gonggong.gpt4j.domain.chat.req.ReqTextContent;
import com.gonggong.gpt4j.domain.chat.res.Content;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptAnalysisService {
    private final ChatAIService chatAIService;
    private final AttemptAnalysisResponsePublisher attemptAnalysisResponsePublisher;

    public void attemptAnalysisForMath(AttemptAnalysisRequestDto dto){
        Content analysis = chatAIService.visionMathQuery(map(dto));

    }

    public VisionReqDto map(AttemptAnalysisRequestDto dto) {
        List<ReqContent> reqContents = dto.getContent().stream()
                .map(content -> {
                    // `messageType`에 따라 적절한 `ReqContent` 타입을 선택합니다.
                    if (dto.getMessageType() == MessageType.TEXT) {
                        return new ReqTextContent(content);
                    } else if (dto.getMessageType() == MessageType.IMAGE_URL) {
                        return new ReqImageContent(new EncodedImage(content));
                    } else {
                        throw new IllegalArgumentException("Unsupported message type: " + dto.getMessageType());
                    }
                })
                .collect(Collectors.toList());

        return new VisionReqDto(reqContents);
    }
}
