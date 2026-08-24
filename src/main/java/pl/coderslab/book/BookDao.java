package pl.coderslab.book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public List<Book> findAll() {
        Query slectBFromBookB = entityManager.createQuery("select b from Book b", Book.class);
        return slectBFromBookB.getResultList();
    }

    // wyszukaj po rating
    public List<Book> findAllByRating(int rating) {
        Query selectBFromBookB = entityManager.createQuery("select b from Book b where b.ratingBook = :rat", Book.class);
        selectBFromBookB.setParameter("rat", rating);
        return selectBFromBookB.getResultList();
    }

    // wyszkuja ktore posiadaja publishera
    public List<Book> findAllByPublisher() {
        Query findPublisher = entityManager.createQuery("select b from Book b where b.publisher is not null", Book.class);
        return findPublisher.getResultList();
    }

    // wyszukaj po wydawcy
    public List<Book> findByPublisher(Long id) {
        Query selectByPublisher = entityManager.createQuery("select b from Book b where b.publisher = :publisher", Book.class);
        selectByPublisher.setParameter("publisher", id);
        return selectByPublisher.getResultList();
    }

//    wyszukaj po autorze
    public List<Book> findByAuthor(String author) {
        Query selectByAuthor = entityManager.createQuery("select b from Book b where b.authors = :author", Book.class);
        selectByAuthor.setParameter("author", author);
        return selectByAuthor.getResultList();
    }
}
