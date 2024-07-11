package com.gonggong.gpt4j.openaiClient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "openai")
@PropertySource("classpath:/apikey.properties")
public class AIConfigProperties {

    private String apiKey;

    @Bean
    public ApiKey getApiKey(){
        System.out.println(" key : "+apiKey);
        return ()-> apiKey;
    }
}
