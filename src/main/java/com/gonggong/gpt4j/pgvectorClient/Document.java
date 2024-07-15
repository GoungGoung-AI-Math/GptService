package com.gonggong.gpt4j.pgvectorClient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "document")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String caption;

    @Column
    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 1536)
    private List<Double> embedding;

    public static Document toEntity(DocumentVo documentVo){
        return Document.builder()
                .name(documentVo.getName())
                .caption(documentVo.getCaption())
                .embedding(documentVo.getEmbedding())
                .build();
    }
}
