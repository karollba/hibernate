package pl.coderslab.author;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.book.Book;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    // dodawanie
    @GetMapping("/add/{firstname}/{lastname}")
    public String add(@PathVariable String firstname, @PathVariable String lastname){
        Author author = new Author();
        author.setFirstName(firstname);
        author.setLastName(lastname);
        authorDao.saveAuthor(author);
        return "ok";
    }

    // pobieranie po id
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id){
        Author author = authorDao.findAuthorById(id);
        if (author == null) {
            return "Nie znaleziono autora o id " + id;
        }
        return author.toString();
    }


    // edycja po id
    @GetMapping("/update/{id}/{firstname}/{lastname}")
    public String update(@PathVariable Long id, @PathVariable String firstname, @PathVariable String lastname){
        Author author = authorDao.findAuthorById(id);
        if (author == null) {
            return "Nie znaleziono autora o id " + id;
        }
        author.setFirstName(firstname);
        author.setLastName(lastname);
        authorDao.update(author);

        return "Zaktualizowano: " + author.getFirstName() + " " + author.getLastName();
    }

    // usuwanie po id
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Author author = authorDao.findAuthorById(id);
        if (author == null) {
            return "Nie znaleziono autora o id " + id;
        }

        authorDao.delete(author);
        return "Usunięto autora o id: " + id;
    }

}
