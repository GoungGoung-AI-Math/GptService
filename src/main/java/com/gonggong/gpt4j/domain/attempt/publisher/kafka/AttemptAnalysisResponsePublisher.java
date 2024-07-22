package com.gonggong.gpt4j.domain.attempt.publisher.kafka;

import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import com.example.demo.my.kafka.infra.kafka.mapper.AttemptAnalysisDataMapper;
import com.example.demo.my.kafka.infra.kafka.producer.KafkaProducer;
import com.gonggong.gpt4j.Gpt4jServiceConfigData;
import com.example.demo.my.kafka.infra.kafka.publisher.kafka.DomainEventPublisher;
import com.gonggong.gpt4j.domain.attempt.event.AttemptAnalysisResponseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttemptAnalysisResponsePublisher implements DomainEventPublisher<AttemptAnalysisResponseEvent> {
    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;
    private final KafkaProducer<String, AttemptAnalysisResponseAvroModel> kafkaProducer;
    private final Gpt4jServiceConfigData gpt4jServiceConfigData;

    @Override
    public void publish(AttemptAnalysisResponseEvent domainEvent) {
        Long attemptId = domainEvent.getAttemptAnalysisDto().getAttemptId();
        log.info("Received AttemptAnalysisResponseEvent for attempt id: {}", attemptId);
        try {
            AttemptAnalysisResponseAvroModel attemptAnalysisResponseAvroModel =
                    attemptAnalysisDataMapper
                            .attemptAnalysisResponseToAvroModel(domainEvent.getAttemptAnalysisDto());

            kafkaProducer.send(gpt4jServiceConfigData.getAttemptAnalysisResponseTopicName(),
                    String.valueOf(attemptId),
                    attemptAnalysisResponseAvroModel);
//                    kafkaMessageHelper.getKafkaCallback(gpt4jServiceConfigData
//                                    .getAttemptAnalysisResponseTopicName(),
//                            attemptAnalysisResponseAvroModel,
//                            String.valueOf(attemptId),
//                            "RestaurantApprovalResponseAvroModel")

            log.info("AttemptAnalysisResponseAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while sending AttemptAnalysisResponseAvroModel message" +
                    " to kafka with order id: {}, error: {}", attemptId, e.getMessage());
        }
    }
}
