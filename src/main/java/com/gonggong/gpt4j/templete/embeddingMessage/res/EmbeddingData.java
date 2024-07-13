package com.gonggong.gpt4j.templete.embeddingMessage.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddingData {
    private String object;
    private Integer index;
    private List<Double> embedding;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("{\n")
                .append("object : ").append(object).append(",\n")
                .append("index : ").append(index).append(",\n")
                .append("embedding : ").append(embedding).append("\n")
                .append("}");
        return sb.toString();
    }
}
