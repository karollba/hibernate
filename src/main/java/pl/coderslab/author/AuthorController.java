package pl.coderslab.author;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.book.Book;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorDao authorDao;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorDao authorDao, AuthorRepository authorRepository) {
        this.authorDao = authorDao;
        this.authorRepository = authorRepository;
    }

    // dodawanie
//    @GetMapping("/add/{firstname}/{lastname}")
//    public String add(@PathVariable String firstname, @PathVariable String lastname){
//        Author author = new Author();
//        author.setFirstName(firstname);
//        author.setLastName(lastname);
//        authorDao.saveAuthor(author);
//        return "ok";
//    }

    @GetMapping("/add/{firstname}/{lastname}/{email}/{pesel}")
    public String add(@PathVariable String firstname,
                      @PathVariable String lastname,
                      @PathVariable String email,
                      @PathVariable String pesel)
    {
        Author author = new Author();
        author.setFirstName(firstname);
        author.setLastName(lastname);
        author.setEmail(email);
        author.setPesel(pesel);

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



    // zad tworzenie zapytan

    @GetMapping("/byemail/{email}")
    public String byEmail(@PathVariable String email) {
        Author author = authorRepository.findByEmail(email);
        if (author == null) {
            return "Not found. Author with email address: " + email;
        }

        return author.toString();
    }

    @GetMapping("/bypesel/{pesel}")
    public String byPesel(@PathVariable String pesel) {
        Author author = authorRepository.findByPesel(pesel);
        if (author == null) {
            return "Not found. Author with pesel: " + pesel;
        }

        return author.toString();
    }

    @GetMapping("/bylastname/{lastName}")
    public List<Author> byLastName(@PathVariable String lastName) {
        return authorRepository.findByLastName(lastName);
    }



    // zapytania szczegolowe @QUery

    @GetMapping("/byemailprefix/{prefix}")
    public List<Author> byEmailPrefixQuery(@PathVariable String prefix) {
        return authorRepository.findByEmailQuery(prefix);
    }

    @GetMapping("/bypeselprefix/{prefix}")
    public List<Author> byPeselPrefixQuery(@PathVariable String prefix) {
        return authorRepository.findByPeselQuery(prefix);
    }

}
