package com.gonggong.gpt4j.fileIO;


import java.util.List;

public interface FileBytesService {

    byte[] getFileBytes(String key);

    List<Document> loadDocuments(byte[] fileData, String fileName);

    List<Document> splitDocuments(List<Document> documents);
    List<Document> splitDocuments(List<Document> documents, int chunkSize, int chunkOverlap);

}

