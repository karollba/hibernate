package pl.coderslab.book;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Book> findAll() {
        return repository.findAll();
    }


    @Transactional
    public Book findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public void update(Long id, String title, int rating, String description) {
        Book book = repository.findById(id).orElse(null);

        if (book == null ) {
            return;
        }
        book.setTitle(title);
        book.setRatingBook(rating);
        book.setDescription(description);
        repository.save(book);
    }



    public void save(Book book) {
        repository.save(book);
    }

    public void delete(Long id) {
      repository.deleteById(id);
    }

    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}

