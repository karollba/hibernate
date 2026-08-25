package pl.coderslab.book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select b from Book b where b.title= ?1")
    List<Book> findBookByTitleWithQuery(String title);

    @Query("select b from Book b where b.category.name=?1")
    List<Book> findWithQueryByCategory(String category);


    @Query("select b from Book b where b.ratingBook between :minRating and :maxRating")
    List<Book> findByRatingQuery(@Param("minRating") int minRating,
                                 @Param("maxRating") int maxRating);

    @Query("select b from Book b where b.publisher=?1")
    List<Book> findByPublisherQuery(Publisher publisher);

    @Query("select b from Book b where b.category.name=?1 order by b.title asc limit 1")
    List<Book> findFirstByTitleQuery(String category);


}
