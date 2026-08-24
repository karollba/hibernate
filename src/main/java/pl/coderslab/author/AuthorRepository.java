package pl.coderslab.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmail(String email);

    Author findByPesel(String pesel);

    List<Author> findByLastName(String lastName);
}
