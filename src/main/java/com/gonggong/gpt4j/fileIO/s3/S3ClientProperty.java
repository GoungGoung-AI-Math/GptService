package com.gonggong.gpt4j.fileIO.s3;

import com.gonggong.gpt4j.fileIO.s3.property.AccessKey;
import com.gonggong.gpt4j.fileIO.s3.property.BucketName;
import com.gonggong.gpt4j.fileIO.s3.property.SecretKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "s3")
@PropertySource("classpath:/apikey.properties")
public class S3ClientProperty {
    private Region region;
    private String bucketName;
    private String accessKey;
    private String secretKey;

    @Bean
    public S3Client getS3client(){
        System.out.println("region : "+region);
        return S3Client.builder().region(region).build();
    }

    @Bean
    public BucketName getBucketName(){
        return ()-> bucketName;
    }

    @Bean
    public AccessKey getAccessKey(){
        return ()-> accessKey;
    }

    @Bean
    public SecretKey getSecretKey(){
        return ()-> secretKey;
    }
}
