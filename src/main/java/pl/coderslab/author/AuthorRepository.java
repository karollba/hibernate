package pl.coderslab.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmail(String email);

    Author findByPesel(String pesel);

    List<Author> findByLastName(String lastName);

    @Query("select a from Author a where a.email like ?1% ")
    List<Author> findByEmailQuery(String prefix);

    @Query("select a from Author a where a.pesel like ?1%")
    List<Author> findByPeselQuery(String prefix);
}
