package com.gonggong.gpt4j.domain.caption;

import com.gonggong.gpt4j.domain.caption.data.ImageCaption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageCaptionRepository extends JpaRepository<ImageCaption, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM ImageCaption ORDER BY embedding <=> cast(? as vector) LIMIT ?")
    List<ImageCaption> findTopNSimilarDocuments(List<Double> embedding, int size);
}