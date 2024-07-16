package com.gonggong.gpt4j.domain.caption.repository;

import com.gonggong.gpt4j.domain.caption.data.ImageCaption;
import com.pgvector.PGvector;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ImageCaptionRepository{

    ImageCaption save(ImageCaption imageCaption);
    List<ImageCaption> findTopNSimilarDocuments(PGvector vector, int size);
}