package pl.coderslab.book;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    // dodawanie
//    @GetMapping("/add/{title}/{description}/{rating}")
//    public String add(@PathVariable String title, @PathVariable String description, @PathVariable int rating){
//
//        Publisher publisher = new Publisher();
//        publisher.setName("PWN");
//        publisherDao.save(publisher);
//
//        Book book = new Book();
//        book.setTitle(title);
//        book.setDescription(description);
//        book.setRatingBook(rating);
//        book.setCreatedOn(LocalDateTime.now());
//
//        book.setPublisher(publisher);
//
//        Author author = new Author();
//        author.getId();
//
//        bookDao.saveBook(book);
//        return "ok";
//    }


    @GetMapping("/bypublisher/{id}")
    public List<Book> byPublisher(@PathVariable Long id) {
        Publisher publisher = publisherDao.findPublisher(id);
        return bookDao.findByPublisher(publisher);
    }


    @GetMapping("/add")
    @ResponseBody
    public String add() {

        Author author1 = authorDao.findAuthorById(1L);
        Author author2 = authorDao.findAuthorById(2L);

        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisherDao.save(publisher);

        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setPublisher(publisher);

        book.getAuthors().add(author1);
        book.getAuthors().add(author2);

        bookDao.saveBook(book);

        Book byId = bookDao.findById(book.getId());

        System.out.println(byId.getId());

        byId.setRatingBook(12);
        bookDao.update(byId);

        return "ok";
    }

    // pobieranie po id
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id){
        Book book = bookDao.findById(id);
        if (book == null) {
            return "Nie znaleziono ksiazki o id " + id;
    }
        return book.toString();
    }

    // edycja po id
    @GetMapping("/update/{id}/{title}/{description}/{rating}")
    public String update(@PathVariable Long id, @PathVariable String title, @PathVariable String description, @PathVariable int rating){
        Book book = bookDao.findById(id);
        if (book == null) {
            return "Nie znaleziono ksiazki o id " + id;
        }
        book.setTitle(title);
        book.setDescription(description);
        book.setRatingBook(rating);
        book.setUpdatedOn(LocalDateTime.now());
        bookDao.update(book);
        return "Zaktualizowano książkę " + book.getTitle() + " o id: " + book.getId();
    }

    // usuwanie po id
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Book book = bookDao.findById(id);
        if (book == null) {
            return "Nie znaleziono ksiazki o id " + id;
        }

        bookDao.delete(book);
        return "Usunięto książkę o id: " + id;
    }

    @GetMapping("/all")
    public List<Book> allBooks() {
        return bookDao.findAll();
    }
}