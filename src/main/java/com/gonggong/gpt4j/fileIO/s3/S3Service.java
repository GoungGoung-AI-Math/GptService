package com.gonggong.gpt4j.fileIO.s3;

import com.gonggong.gpt4j.fileIO.Document;
import com.gonggong.gpt4j.fileIO.FileBytesService;
import com.gonggong.gpt4j.fileIO.s3.dto.AccessKey;
import com.gonggong.gpt4j.fileIO.s3.dto.BucketName;
import com.gonggong.gpt4j.fileIO.s3.dto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service implements FileBytesService {
    private final S3Client s3Client;
    private final BucketName bucketName;
    private final AccessKey accessKey;
    private final SecretKey secretKey;


    public byte[] getFileBytes(String keyName) {
        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName.getBucketName())
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();

            System.out.println("Successfully obtained bytes from an S3 object");

            return data;

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public List<Document> loadDocuments(byte[] fileData, String fileName) {
        List<Document> documents = new ArrayList<>();
        try (PDDocument pdfDocument = PDDocument.load(new ByteArrayInputStream(fileData))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdfDocument);
            Document document = new Document(fileName, text);
            documents.add(document);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return documents;
    }

    @Override
    public List<Document> splitDocuments(List<Document> documents, int chunkSize, int chunkOverlap) {
        List<Document> chunks = new ArrayList<>();
        for (Document doc : documents) {
            String text = doc.getText();
            for (int start = 0; start < text.length(); start += chunkSize - chunkOverlap) {
                int end = Math.min(start + chunkSize, text.length());
                String chunkText = text.substring(start, end);
                Document chunk = new Document(doc.getName(), chunkText);
                chunk.setMetadata("source", doc.getName());
                chunk.setMetadata("page", Integer.toString(start / chunkSize + 1));
                chunks.add(chunk);
            }
        }
        return chunks;
    }
    @Override
    public List<Document> splitDocuments(List<Document> documents) {
        List<Document> chunks = new ArrayList<>();
        int chunkSize = 800;
        int chunkOverlap = 80;
        for (Document doc : documents) {
            String text = doc.getText();
            for (int start = 0; start < text.length(); start += chunkSize - chunkOverlap) {
                int end = Math.min(start + chunkSize, text.length());
                String chunkText = text.substring(start, end);
                Document chunk = new Document(doc.getName(), chunkText);
                chunk.setMetadata("source", doc.getName());
                chunk.setMetadata("page", Integer.toString(start / chunkSize + 1));
                chunks.add(chunk);
            }
        }
        return chunks;
    }
}
