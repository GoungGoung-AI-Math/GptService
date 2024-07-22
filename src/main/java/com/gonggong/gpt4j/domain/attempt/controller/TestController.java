package com.gonggong.gpt4j.domain.attempt.controller;

import com.example.demo.my.kafka.infra.kafka.dtos.AnalysisType;
import com.example.demo.my.kafka.infra.kafka.dtos.MessageType;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisResponseDto;
import com.gonggong.gpt4j.domain.attempt.event.AttemptAnalysisResponseEvent;
import com.gonggong.gpt4j.domain.attempt.publisher.kafka.AttemptAnalysisResponsePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final AttemptAnalysisResponsePublisher attemptAnalysisResponsePublisher;

    @PostMapping("/test-kafka")
    public void test(){
        AttemptAnalysisResponseDto dto = AttemptAnalysisResponseDto.builder()
                .analysisType(AnalysisType.ATTEMPT)
                .messageType(MessageType.TEXT)
                .attemptId(1L)
                .content("테스트 가러 갑니다!!~ kafka야 동작해야!!")
                        .build();
        AttemptAnalysisResponseEvent event = AttemptAnalysisResponseEvent.builder()
                .attemptAnalysisDto(dto)
                .attemptAnalysisEventDomainEventPublisher(attemptAnalysisResponsePublisher)
                .createdAt(ZonedDateTime.now())
                .failureMessages(List.of())
                .build();
        attemptAnalysisResponsePublisher.publish(event);
    }
}
