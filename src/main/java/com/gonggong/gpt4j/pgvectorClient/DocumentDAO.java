package com.gonggong.gpt4j.pgvectorClient;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DocumentDAO {
//    private final EntityManagerFactory entityManagerFactory;
//
//    public void saveDocument(DocumentVo documentVo){
//        EntityManager em = entityManagerFactory.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try{
//            tx.begin();
//            em.persist(Document.toEntity(documentVo));
//            tx.commit();
//
//        } catch (IllegalStateException | PersistenceException e){
//            // 어떤 이유에서 오류가 났다면 트랜잭션을 롤백 시켜줘야한다.
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//    }
//
//    public void searchSimilarDocuments(Double[] embeddings){
//        EntityManager em = entityManagerFactory.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try{
//            tx.begin();
//            List<Document> similarDocuments = em
//                    .createQuery("SELECT d FROM Document d ORDER BY cosine_distance(embedding, :embedding) LIMIT 5", Document.class)
//                    .setParameter("embedding", new float[] {1, 1, 1})
//                    .getResultList();
//            tx.commit();
//
//        } catch (IllegalStateException | PersistenceException e){
//            // 어떤 이유에서 오류가 났다면 트랜잭션을 롤백 시켜줘야한다.
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//    }
}
