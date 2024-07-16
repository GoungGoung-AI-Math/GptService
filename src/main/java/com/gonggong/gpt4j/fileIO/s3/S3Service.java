package com.gonggong.gpt4j.fileIO.s3;

import com.gonggong.gpt4j.fileIO.Document;
import com.gonggong.gpt4j.fileIO.FileBytesService;
import com.gonggong.gpt4j.fileIO.s3.property.AccessKey;
import com.gonggong.gpt4j.fileIO.s3.property.BucketName;
import com.gonggong.gpt4j.fileIO.s3.property.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import org.apache.pdfbox.pdmodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service implements FileBytesService {
    private final S3Client s3Client;
    private final BucketName bucketName;
    private final AccessKey accessKey;
    private final SecretKey secretKey;
    private final Pattern pattern = Pattern.compile("\\b\\d+\\.");


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
    public Document loadDocuments(byte[] fileData, String fileName) {
        try (PDDocument pdfDocument = PDDocument.load(new ByteArrayInputStream(fileData))) {
//            PDFTextStripper stripper = new PDFTextStripper();
            // UTF-16을 사용하는 PDFTextStripper 생성 예시
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdfDocument);
            return new Document(fileName, text);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<Document> splitDocuments(Document doc) {
        List<Document> chunks = new ArrayList<>();
        String text = doc.getText();
        Matcher matcher = pattern.matcher(text);
        int start = 0;
        int chunkNumber = 1;
        while (matcher.find()) {
            int end = matcher.start();
            String chunkText = text.substring(start, end).trim();
            if (!chunkText.isEmpty()) {
                Document chunk = new Document(doc.getName() + "_" + chunkNumber, chunkText);
                chunk.setMetadata("source", doc.getName());
                chunks.add(chunk);
                chunkNumber++;
            }
            start = matcher.start();
        }
        String lastChunkText = text.substring(start).trim();
        if (!lastChunkText.isEmpty()) {
            Document chunk = new Document(doc.getName() + "_" + chunkNumber, lastChunkText);
            chunk.setMetadata("source", doc.getName());
            chunks.add(chunk);
        }
        return chunks;
    }



    @Override
    public void saveDocuments(List<Document> documents, String outputDirectory) {
        for (Document doc : documents) {
            String outputFileName = outputDirectory + File.separator + doc.getName() + ".pdf";
            try (PDDocument pdfDocument = new PDDocument()) {
                PDPage page = new PDPage();
                pdfDocument.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(25, 725);
                contentStream.setLeading(14.5f);

                String[] lines = doc.getText().split("\n");
                for (String line : lines) {
                    contentStream.showText(line);
                    contentStream.newLine();
                }
                contentStream.endText();
                contentStream.close();

                pdfDocument.save(outputFileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save document: " + e.getMessage(), e);
            }
        }
    }
}
