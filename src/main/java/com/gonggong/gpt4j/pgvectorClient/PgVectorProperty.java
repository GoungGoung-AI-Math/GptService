package com.gonggong.gpt4j.pgvectorClient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.ai.vectorstore.pgvector")
public class PgVectorProperty {
//    private String indexType;
//    private String distanceType;
//    private Integer dimensions;



}
