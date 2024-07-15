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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document{name='").append(name).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", metadata=").append(metadata);
        sb.append('}');
        return sb.toString();
    }
    public void setMetadata(String key, String value) {
        this.metadata.put(key, value);
    }
}
