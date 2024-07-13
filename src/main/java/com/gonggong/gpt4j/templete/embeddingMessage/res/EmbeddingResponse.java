package com.gonggong.gpt4j.templete.embeddingMessage.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gonggong.gpt4j.templete.consts.Usage;
import com.gonggong.gpt4j.templete.embeddingMessage.res.EmbeddingData;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddingResponse {
    private String object;
    private List<EmbeddingData> data;
    private String model;
    private Usage usage;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("{\n")
                .append("object : ").append(object).append(",\n")
                .append("model : ").append(model).append(",\n")
                .append("usage : ").append(usage).append(",\n");

        if (data != null && !data.isEmpty()) {
            sb.append("data : [\n");
            for (EmbeddingData ed : data) {
                sb.append(ed).append('\n');
            }
            sb.append("]\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
