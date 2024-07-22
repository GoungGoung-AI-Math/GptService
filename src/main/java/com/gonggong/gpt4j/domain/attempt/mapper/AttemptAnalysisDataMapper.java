package com.gonggong.gpt4j.domain.attempt.mapper;

import com.example.demo.my.kafka.infra.avrobuild.AnalysisType;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisRequestAvroModel;
import com.example.demo.my.kafka.infra.avrobuild.AttemptAnalysisResponseAvroModel;
import com.example.demo.my.kafka.infra.avrobuild.MessageType;
import com.gonggong.gpt4j.domain.attempt.event.AttemptAnalysisDto;
import org.springframework.stereotype.Component;


@Component
public class AttemptAnalysisDataMapper {
    public AttemptAnalysisRequestAvroModel attemptAnalysisRequestToAvroModel(AttemptAnalysisDto attemptAnalysisDto){
        return AttemptAnalysisRequestAvroModel.newBuilder()
                .setAttemptId(attemptAnalysisDto.getAttemptId())
                .setAnalysisType(AnalysisType.valueOf(
                        attemptAnalysisDto.getAnalysisType().name()
                ))
                .setMessageType(MessageType.valueOf(
                        attemptAnalysisDto.getMessageType().name()
                ))
                .setContent(attemptAnalysisDto.getContent())
                .build();
    }

    public AttemptAnalysisResponseAvroModel attemptAnalysisResponseToAvroModel(AttemptAnalysisDto attemptAnalysisDto){
        return AttemptAnalysisResponseAvroModel.newBuilder()
                .setAttemptId(attemptAnalysisDto.getAttemptId())
                .setAnalysisType(AnalysisType.valueOf(
                        attemptAnalysisDto.getAnalysisType().name()
                ))
                .setMessageType(MessageType.valueOf(
                        attemptAnalysisDto.getMessageType().name()
                ))
                .setContent(attemptAnalysisDto.getContent())
                .build();
    }

    public AttemptAnalysisDto avroModelToAttemptAnalysisDto(AttemptAnalysisResponseAvroModel avroModel){
        return AttemptAnalysisDto.builder()
                .attemptId(avroModel.getAttemptId())
                .analysisType(AnalysisType.valueOf(avroModel.getAnalysisType().name()))
                .messageType(
                        com.gonggong.gpt4j.consts.MessageType.valueOf(avroModel.getMessageType().name()))
                .content(avroModel.getContent())
                .build();
    }

    public AttemptAnalysisDto avroModelToAttemptAnalysisDto(AttemptAnalysisRequestAvroModel avroModel){
        return AttemptAnalysisDto.builder()
                .attemptId(avroModel.getAttemptId())
                .analysisType(AnalysisType.valueOf(avroModel.getAnalysisType().name()))
                .messageType(
                        com.gonggong.gpt4j.consts.MessageType.valueOf(avroModel.getMessageType().name()))
                .content(avroModel.getContent())
                .build();
    }
}
