package com.gonggong.gpt4j.domain.attempt.listener;

import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisRequestAvroModel;
import com.example.demo.my.kafka.infra.kafka.dtos.attempt.analysis.AttemptAnalysisRequestDto;
import com.example.demo.my.kafka.infra.kafka.listener.kafka.KafkaConsumer;

import com.example.demo.my.kafka.infra.kafka.mapper.AttemptAnalysisDataMapper;
import com.gonggong.gpt4j.domain.attempt.AttemptAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class AttemptAnalysisKafkaListener implements KafkaConsumer<AttemptAnalysisRequestAvroModel> {

    private final AttemptAnalysisDataMapper attemptAnalysisDataMapper;
    private final AttemptAnalysisService attemptAnalysisService;

    @Override
    @KafkaListener(id = "${kafka-consumer.attempt-analysis-consumer-group-id}", topics = "${gpt4j-service.attempt-analysis-request-topic-name}")
    public void receive(@Payload List<AttemptAnalysisRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of analysis responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(avroModel -> {
            AttemptAnalysisRequestDto dto = attemptAnalysisDataMapper.avroModelToAttemptAnalysisRequestDto(avroModel);
            attemptAnalysisService.attemptAnalysisForMath(dto);
            log.info("Processing successful analysis for attempt id: {} ", dto.getAttemptId());
        });
    }
}