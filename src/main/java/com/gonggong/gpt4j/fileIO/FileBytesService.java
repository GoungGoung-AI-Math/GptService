package com.gonggong.gpt4j.fileIO;


import java.util.List;
import java.util.regex.Pattern;

public interface FileBytesService {

    byte[] getFileBytes(String key);

    Document loadDocuments(byte[] fileData, String fileName);

    List<Document> splitDocuments(Document document);

    void saveDocuments(List<Document> documents, String outputDirectory);
}

