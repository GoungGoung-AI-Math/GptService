package com.gonggong.gpt4j.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gonggong.gpt4j.clients.openaiClient.EmbeddingClient;
import com.gonggong.gpt4j.dto.PdfFileURLDto;
import com.gonggong.gpt4j.fileIO.Document;
import com.gonggong.gpt4j.fileIO.FileBytesService;
import com.gonggong.gpt4j.templete.embeddingMessage.req.EmbeddingMessage;
import com.gonggong.gpt4j.templete.embeddingMessage.res.EmbeddingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextEmbeddingService {
    private final EmbeddingClient embeddingClient;
    private final FileBytesService s3Service;

    public EmbeddingResponse embeddingSimpleText(String text){
        EmbeddingMessage embeddingMessage = new EmbeddingMessage(text);
        EmbeddingResponse response = embeddingClient.sendPostRequest(embeddingMessage);
        log.info(response.toString());
        return response;
    }

    public List<EmbeddingResponse> getEmbeddingObjectFromS3(PdfFileURLDto pdfFileURLDto){
        byte[] fileData = s3Service.getFileBytes(pdfFileURLDto.getUrl());
        List<Document> documents = s3Service.loadDocuments(fileData, pdfFileURLDto.getPdfName());
        List<Document> splitDocuments = s3Service.splitDocuments(documents, 500, 80);
        List<EmbeddingMessage> embeddingMessages = splitDocuments.stream()
                .map(document -> new EmbeddingMessage(document.getText())).toList();
        return embeddingMessages.parallelStream()
                .map(embeddingClient::sendPostRequest).toList();
    }

    private static String getEmbeddingMessageJson(EmbeddingMessage embeddingMessage){
        String message;
        try{
            message = embeddingMessage.toJson();
        } catch (JsonProcessingException e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return message;
    }
}
