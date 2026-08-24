package pl.coderslab.book;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.publisher.Publisher2;

import java.util.List;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public List<Publisher> findAllPublishers() {
        Query allPublishers = entityManager.createQuery("select p from Publisher p", Publisher.class);
        return  allPublishers.getResultList();
    }

    public Publisher findPublisher(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void updatePublisher(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public void deletePublisher(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }
}
