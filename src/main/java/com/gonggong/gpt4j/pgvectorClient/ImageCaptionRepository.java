package com.gonggong.gpt4j.pgvectorClient;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageCaptionRepository extends JpaRepository<ImageCaption, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM ImageCaption ORDER BY embedding <=> cast(? as vector) LIMIT ?")
    List<ImageCaption> findTopNSimilarDocuments(Double[] embedding, int size);
}