package pl.coderslab.book;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Transactional
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Transactional
    public Publisher findByNip(String nip) {
        return publisherRepository.findByNip(nip);
    }


    @Transactional
    public Publisher findByRegon(String regon) {
        return publisherRepository.findByRegon(regon);
    }

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return publisherRepository.existsById(id);
    }

    @Transactional
    public void update(Long id, String name, String nip, String regon) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);

        publisher.setRegon(regon);
        publisher.setNip(nip);
        publisher.setName(name);

        publisherRepository.save(publisher);
    }

}
