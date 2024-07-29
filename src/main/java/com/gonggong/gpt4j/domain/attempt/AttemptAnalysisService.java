package com.gonggong.gpt4j.domain.attempt;

import com.gonggong.gpt4j.domain.VisionReqDto;
import com.gonggong.gpt4j.domain.attempt.event.AttemptAnalysisResponseEvent;
import com.gonggong.gpt4j.domain.attempt.publisher.kafka.AttemptAnalysisResponsePublisher;
import com.gonggong.gpt4j.domain.chat.ChatAIService;
import com.gonggong.gpt4j.domain.chat.req.EncodedImage;
import com.gonggong.gpt4j.domain.chat.req.ReqContent;
import com.gonggong.gpt4j.domain.chat.req.ReqImageContent;
import com.gonggong.gpt4j.domain.chat.req.ReqTextContent;
import com.gonggong.gpt4j.domain.chat.res.Content;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import math.ai.my.kafka.infra.kafka.dtos.AnalysisType;
import math.ai.my.kafka.infra.kafka.dtos.MessageType;
import math.ai.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
import math.ai.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisResponseDto;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptAnalysisService {
    private final ChatAIService chatAIService;
    private final AttemptAnalysisResponsePublisher attemptAnalysisResponsePublisher;

    public void attemptAnalysisForMath(AttemptAnalysisRequestDto dto){
        Content analysis = chatAIService.visionMathQuery(mapRequestToDto(dto));
        AttemptAnalysisResponseEvent event = mapContentToResponseEvent(dto.getAttemptId(), analysis);
        attemptAnalysisResponsePublisher.publish(event);
    }

    public VisionReqDto mapRequestToDto(AttemptAnalysisRequestDto dto) {
        List<ReqContent> reqContents = dto.getContents().stream()
                .map(content -> {
                    // `messageType`에 따라 적절한 `ReqContent` 타입을 선택합니다.
                    if (content.getMessageType() == MessageType.TEXT) {
                        return new ReqTextContent(content.getContent());
                    } else if (content.getMessageType() == MessageType.IMAGE_URL) {
                        return new ReqImageContent(new EncodedImage(content.getContent()));
                    } else {
                        throw new IllegalArgumentException("Unsupported message type: " + content.getMessageType());
                    }
                })
                .collect(Collectors.toList());

        return new VisionReqDto(reqContents);
    }

    public AttemptAnalysisResponseEvent mapContentToResponseEvent(Long attemptId, Content content){
        AttemptAnalysisResponseDto dto = AttemptAnalysisResponseDto.builder()
                .attemptId(attemptId)
                .analysisType(AnalysisType.ATTEMPT)
                .messageType(MessageType.TEXT)
                .content(content.getMessage().getValue())
                .build();
        return AttemptAnalysisResponseEvent.builder()
                .attemptAnalysisDto(dto)
                .attemptAnalysisEventDomainEventPublisher(attemptAnalysisResponsePublisher)
                .createdAt(ZonedDateTime.now())
                .failureMessages(List.of())
                .build();

    }
}
