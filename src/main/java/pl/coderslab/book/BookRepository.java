package pl.coderslab.book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.author.Author;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByCategory(Category category);

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByAuthors(Author authors);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByRatingBook(int rating);

    Book findFirstByCategoryOrderByTitleAsc(Category category);

}
