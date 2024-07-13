package com.gonggong.gpt4j.elasticClient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@PropertySource("classpath:/elasticsearch.properties")
public class ElasticsearchProperty {

  private String host;
  private int port;
  private String apiKey;
}