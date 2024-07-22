package com.gonggong.gpt4j.domain.attempt.controller;

import com.example.demo.my.kafka.infra.avrobuild.AnalysisType;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import com.gonggong.gpt4j.consts.MessageType;
import com.gonggong.gpt4j.domain.attempt.event.AttemptAnalysisDto;
import com.gonggong.gpt4j.domain.attempt.event.AttemptAnalysisEvent;
import com.gonggong.gpt4j.domain.attempt.publisher.kafka.AttemptAnalysisResponsePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final AttemptAnalysisResponsePublisher attemptAnalysisResponsePublisher;

    @PostMapping("/test-kafka")
    public void test(){
        AttemptAnalysisDto dto = AttemptAnalysisDto.builder()
                .analysisType(AnalysisType.ATTEMPT)
                .messageType(MessageType.TEXT)
                .attemptId(1L)
                .content("테스트 가러 갑니다!!~ kafka야 동작해야!!")
                        .build();
        AttemptAnalysisEvent event = AttemptAnalysisEvent.builder()
                .attemptAnalysisDto(dto)
                .attemptAnalysisEventDomainEventPublisher(attemptAnalysisResponsePublisher)
                .createdAt(ZonedDateTime.now())
                .failureMessages(List.of())
                .build();
        attemptAnalysisResponsePublisher.publish(event);
    }
}
