package pl.coderslab.book;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByNip(String nip);

    Publisher findByRegon(String regon);

}
