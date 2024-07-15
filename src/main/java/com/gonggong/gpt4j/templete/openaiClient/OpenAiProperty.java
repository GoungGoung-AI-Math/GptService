package com.gonggong.gpt4j.templete.openaiClient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "openai")
@PropertySource("classpath:/apikey.properties")
public class OpenAiProperty {

    private String apiKey;

    @Bean
    public ApiKey getApiKey(){
//        System.out.println(" key : "+apiKey);
        return ()-> apiKey;
    }
}
