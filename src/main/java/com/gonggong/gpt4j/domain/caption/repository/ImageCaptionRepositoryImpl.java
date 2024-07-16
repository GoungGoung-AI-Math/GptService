package com.gonggong.gpt4j.domain.caption.repository;

import com.gonggong.gpt4j.domain.caption.data.ImageCaption;
import com.pgvector.PGvector;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImageCaptionRepositoryImpl implements ImageCaptionRepository{
    private final EntityManagerFactory emf;
    @Override
    public ImageCaption save(ImageCaption imageCaption) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            ImageCaption mergedImageCaption = em.merge(imageCaption);
            transaction.commit();
            return mergedImageCaption;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<ImageCaption> findTopNSimilarDocuments(PGvector vector, int size) {
        EntityManager em = emf.createEntityManager();
        try {
            List<ImageCaption> items = em
                    .createNativeQuery("SELECT * FROM ImageCaption ORDER BY embedding <=> CAST(?1 AS vector)", ImageCaption.class)
                    .setParameter(1, vector.getValue())
                    .setMaxResults(size)
                    .getResultList();
            return items;
        } finally {
            em.close();
        }
    }
}
