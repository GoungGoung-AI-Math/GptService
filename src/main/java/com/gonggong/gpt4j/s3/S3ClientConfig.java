package com.gonggong.gpt4j.s3;

import com.gonggong.gpt4j.config.ApiKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "s3")
@PropertySource("classpath:/apikey.properties")
public class S3ClientConfig {
    private Region region;
    private String bucketName;
    private String accessKey;
    private String secretKey;

    @Bean
    public S3Client getS3client(){
        System.out.println("region : "+region);
        return S3Client.builder().region(region).build();
    }
}
