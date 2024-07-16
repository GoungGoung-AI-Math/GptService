package com.gonggong.gpt4j;

import com.gonggong.gpt4j.domain.openai.gptClient.OpenAiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OpenAiProperty.class)
public class Gpt4jApplication {
    public static void main(String[] args) {
        SpringApplication.run(Gpt4jApplication.class, args);
    }
}
