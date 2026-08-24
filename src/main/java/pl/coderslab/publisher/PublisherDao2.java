package pl.coderslab.publisher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PublisherDao2 {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePublisher(Publisher2 publisher) {
        entityManager.persist(publisher);
    }

    public Publisher2 findPublisher(long id) {
        return entityManager.find(Publisher2.class, id);
    }

    public void updatePublisher(Publisher2 publisher) {
        entityManager.merge(publisher);
    }

    public void deletePublisher(Publisher2 publisher) {
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }
}


