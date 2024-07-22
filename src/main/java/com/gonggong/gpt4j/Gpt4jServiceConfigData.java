package com.gonggong.gpt4j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "gpt4j-service")
public class Gpt4jServiceConfigData {
    private String AttemptAnalysisRequestTopicName;
    private String AttemptAnalysisResponseTopicName;
}
