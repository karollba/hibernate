package pl.coderslab.publisher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;


@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    // dodawanie
    @GetMapping("/add/{name}")
    public String add(@PathVariable String name){
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherDao.savePublisher(publisher);
        return "ok";
    }

    // pobieranie po id
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id){
        Publisher publisher = publisherDao.findPublisher(id);
        if (publisher == null) {
            return "Nie znaleziono wydawcy o id " + id;
        }
        return publisher.toString();
    }


    // edycja po id
    @GetMapping("/update/{id}/{publisherName}")
    public String update(@PathVariable Long id, @PathVariable String publisherName){
        Publisher publisher = publisherDao.findPublisher(id);
        if (publisher == null) {
            return "Nie znaleziono wydawcy o id " + id;
        }
        publisher.setName(publisherName);
        publisherDao.updatePublisher(publisher);

        return "Zaktualizowano: " + publisher.getName();
    }

    // usuwanie po id
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Publisher publisher = publisherDao.findPublisher(id);
        if (publisher == null) {
            return "Nie znaleziono wydawcy o id " + id;
        }

        publisherDao.deletePublisher(publisher);
        return "Usunięto wydawcę o id: " + id;
    }

}
