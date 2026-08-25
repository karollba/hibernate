package pl.coderslab.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.book.Publisher;
import pl.coderslab.book.PublisherDao;
import pl.coderslab.book.PublisherRepository;
import pl.coderslab.book.PublisherService;

import java.util.List;


@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/all")
    public List<Publisher> all() {
        return publisherService.findAll();
    }

    // wyszukiwanie po nip
    @GetMapping("/bynip/{nip}")
    public Publisher byNip(@PathVariable String nip) {
        return publisherService.findByNip(nip);
    }

    @GetMapping("/byregon/{regon}")
    public Publisher byRegon(@PathVariable String regon) {
        return publisherService.findByRegon(regon);
    }

    // dodawanie
    @GetMapping("/add/{name}/{nip}/{regon}")
    public String add(@PathVariable String name,
                      @PathVariable String nip,
                      @PathVariable String regon) {

        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setNip(nip);
        publisher.setRegon(regon);
        publisherService.save(publisher);
        return "ok";
    }

    // pobieranie po id
    @GetMapping("/get/{id}")
    public Publisher get(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @GetMapping("/update/{id}/{publisherName}/{nip}/{regon}")
    public String update(@PathVariable Long id,
                         @PathVariable String publisherName,
                         @PathVariable String nip,
                         @PathVariable String regon) {
        if (!publisherService.exists(id)) {
            return "Not found id: " + id;
        }
        publisherService.update(id, publisherName, nip, regon);
        return "Updated: " + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!publisherService.exists(id)) {
            return "nie znaleziono id" + id;
        }
        publisherService.delete(id);
        return "Usunieto " + id;
    }
}