package com.gonggong.gpt4j.domain.attempt.event;

import com.example.demo.my.kafka.infra.avrobuild.AnalysisType;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEvent;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gonggong.gpt4j.consts.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class AttemptAnalysisDto{
    private Long attemptId;
    private AnalysisType analysisType;
    private MessageType messageType;
    private String content;

}
