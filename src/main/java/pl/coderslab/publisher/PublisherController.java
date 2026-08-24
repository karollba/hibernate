package pl.coderslab.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.book.Publisher;
import pl.coderslab.book.PublisherDao;
import pl.coderslab.book.PublisherRepository;


@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherDao publisherDao;

    @Autowired
    private PublisherRepository publisherRepository;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }


    // wyszukiwanie po nip

    @GetMapping("/bynip/{nip}")
    public String byNip(@PathVariable String nip) {
        Publisher publisher = publisherRepository.findByNip(nip);
        if (publisher == null) {
            return "Nie znaleziono wydawcy o nr nip: " + nip;
        }

        return  publisher.toString();
    }

    @GetMapping("/byregon/{regon}")
    public String byRegon(@PathVariable String regon) {
        Publisher publisher = publisherRepository.findByRegon(regon);
        if (publisher == null) {
            return "Nie znaleziono wydawcy o nr regon: " + regon;
        }

        return  publisher.toString();
    }

    // dodawanie
    @GetMapping("/add/{name}/{nip}/{regon}")
    public String add(@PathVariable String name,
                      @PathVariable String nip,
                      @PathVariable String regon){

        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setNip(nip);
        publisher.setRegon(regon);
        publisherDao.save(publisher);
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
//    @GetMapping("/update/{id}/{nip}/{regon}")
//    public String update(@PathVariable Long id,
//                         @PathVariable String nip,
//                         @PathVariable String regon){
//        Publisher publisher = publisherDao.findPublisher(id);
//        if (publisher == null) {
//            return "Nie znaleziono wydawcy o id " + id;
//        }
//        publisher.setNip(nip);
//        publisher.setRegon(regon);
//        publisherDao.updatePublisher(publisher);
//
//        return "Zaktualizowano: " + publisher.getName();
//    }

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
