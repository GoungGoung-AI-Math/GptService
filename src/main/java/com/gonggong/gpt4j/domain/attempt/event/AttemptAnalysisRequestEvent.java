package com.gonggong.gpt4j.domain.attempt.event;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import math.ai.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
import math.ai.my.kafka.infra.kafka.listener.DomainEvent;
import math.ai.my.kafka.infra.kafka.listener.DomainEventPublisher;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttemptAnalysisRequestEvent implements DomainEvent<AttemptAnalysisRequestEvent> {
    private final AttemptAnalysisRequestDto attemptAnalysisDto;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;
    private final DomainEventPublisher<AttemptAnalysisRequestEvent> attemptAnalysisEventDomainEventPublisher;

    @Override
    public void fire() {
        attemptAnalysisEventDomainEventPublisher.publish(this);
    }
}
