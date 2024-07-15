package com.gonggong.gpt4j.pgvectorClient;
import com.pgvector.PGvector;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.persistence.EntityManagerFactory;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class PgVectorConfig {
    private String url;
    private String username;
    private String password;

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        return Persistence.createEntityManagerFactory("default");
    }

}
