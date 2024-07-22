package com.gonggong.gpt4j.domain.attempt.publisher.kafka;

import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import com.example.demo.my.kafka.infra.kafka.producer.KafkaMessageHelper;
import com.example.demo.my.kafka.infra.kafka.producer.KafkaProducer;
import com.gonggong.gpt4j.Gpt4jServiceConfigData;
import com.gonggong.gpt4j.domain.attempt.mapper.AttemptAnalysisDataMapper;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;
import com.gonggong.gpt4j.domain.attempt.event.AttemptAnalysisEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttemptAnalysisResponsePublisher implements DomainEventPublisher<AttemptAnalysisEvent> {
    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;
    private final KafkaProducer<Long, AttemptAnalysisResponseAvroModel> kafkaProducer;
    private final Gpt4jServiceConfigData gpt4jServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    @Override
    public void publish(AttemptAnalysisEvent domainEvent) {
        Long attemptId = domainEvent.getAttemptAnalysisDto().getAttemptId();
        log.info("Received AttemptAnalysisEvent for attempt id: {}", attemptId);
        try {
            AttemptAnalysisResponseAvroModel attemptAnalysisResponseAvroModel =
                    attemptAnalysisDataMapper
                            .attemptAnalysisResponseToAvroModel(domainEvent.getAttemptAnalysisDto());

            kafkaProducer.send(gpt4jServiceConfigData.getAttemptAnalysisResponseTopicName(),
                    attemptId,
                    attemptAnalysisResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(gpt4jServiceConfigData
                                    .getAttemptAnalysisResponseTopicName(),
                            attemptAnalysisResponseAvroModel,
                            attemptId,
                            "RestaurantApprovalResponseAvroModel"));

            log.info("AttemptAnalysisResponseAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            log.error("Error while sending AttemptAnalysisResponseAvroModel message" +
                    " to kafka with order id: {}, error: {}", attemptId, e.getMessage());
        }
    }
}
