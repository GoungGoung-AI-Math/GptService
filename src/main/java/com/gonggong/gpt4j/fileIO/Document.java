package com.gonggong.gpt4j.fileIO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private String name;
    private String text;
    private Map<String, String> metadata = new HashMap<>();

    public Document(String name, String text) {
        this.name = name;
        this.text = text;
        this.metadata = new HashMap<>();
    }

    public void setMetadata(String key, String value) {
        this.metadata.put(key, value);
    }
}
