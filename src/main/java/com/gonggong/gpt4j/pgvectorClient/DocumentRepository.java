package com.gonggong.gpt4j.pgvectorClient;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT d FROM Document d ORDER BY cosine_distance(d.embedding, :embedding)")
    List<Document> findTopNSimilarDocuments(@Param("embedding") Double[] embedding, Pageable pageable);
}