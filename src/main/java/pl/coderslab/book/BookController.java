package pl.coderslab.book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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


    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final CategoryRepository categoryRepository;


    public BookController(BookDao bookDao, PublisherDao publisherDao, BookRepository bookRepository, AuthorDao authorDao, CategoryRepository categoryRepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.bookRepository = bookRepository;
        this.authorDao = authorDao;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all2")
    public List<Book> allBooks2() {
        return bookRepository.findAll();
    }


    @GetMapping("/bytitle/{title}")
    public List<Book> byTitle(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    @GetMapping("/bycategory/{id}")
    public List<Book> byCategory(@PathVariable Long id) {
        return bookRepository.findByCategoryId(id);
    }

    @GetMapping("/bycategories/{category}")
    public List<Book> byCategory(@PathVariable Category category) {
        return bookRepository.findByCategory(category);
    }


    @GetMapping("/addcategory")
    public String addCategory(){
        Category category = new Category();
        category.setName("programming");
        categoryRepository.save(category);
        return "ok";
    }


    @GetMapping("/bypublisher/{id}")
    public List<Book> byPublisher(@PathVariable Long id) {
        Publisher publisher = publisherDao.findPublisher(id);
        return bookDao.findByPublisher(publisher);
    }


    @GetMapping("/add")
    @ResponseBody
    public String add() {

        Category category = new Category();
        category.setName("Programming");
        categoryRepository.save(category);

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

        book.setCategory(category);

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



    // zadania z tworzenia zapytan

    @GetMapping("/byauthor/{id}")
    public List<Book> byAuthor(@PathVariable Long id) {
        Author author = authorDao.findAuthorById(id);
        return bookRepository.findByAuthors(author);
    }

    @GetMapping("/bypublisher2/{id}")
    public List<Book> byPublisher2(@PathVariable Long id) {
        Publisher publisher = publisherDao.findPublisher(id);
        return bookRepository.findByPublisher(publisher);
    }

    @GetMapping("/byrating/{rating}")
    public List<Book> byRating(@PathVariable int rating) {
        return bookRepository.findByRatingBook(rating);
    }

    @GetMapping("/firstbycategory/{id}")
    public Book firstByCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return bookRepository.findFirstByCategoryOrderByTitleAsc(category);
    }


    // zapytania szczegolowe @QUery

    @GetMapping("/bytitlequery/{title}")
    public List<Book> findByTitleQuery(@PathVariable String title) {
        return bookRepository.findBookByTitleWithQuery(title);
    }

    @GetMapping("/bycategoryquery/{category}")
    public List<Book> findByCategoryQuery(@PathVariable String category) {
        return bookRepository.findWithQueryByCategory(category);
    }

    @GetMapping("/bypublisherquery/{publisher}")
    public List<Book> findByPublisherQuery(@PathVariable Publisher publisher) {
        return bookRepository.findByPublisherQuery(publisher);
    }

    @GetMapping("/byratingquery/{minRating}/{maxRating}")
    public List<Book> findByPublisherQuery(@PathVariable int minRating,
                                           @PathVariable int maxRating) {
        return bookRepository.findByRatingQuery(minRating, maxRating);
    }

    @GetMapping("/bycategoryascquery/{category}")
    public List<Book> findByCategoryAscQuery(@PathVariable String category) {
        return bookRepository.findFirstByTitleQuery(category);
    }


}





//     dodawanie
//    @GetMapping("/add/{title}/{description}/{rating}")
//    public String add(@PathVariable String title, @PathVariable String description, @PathVariable int rating){
//
//        Category category = new Category();
//        category.setName("Programming");
//        categoryRepository.save(category);
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
//        book.setCategory(category);
//
//        book.setCategory(category);
//
//        Author author = new Author();
//        author.getId();
//
//        bookDao.saveBook(book);
//        return "ok";
//    }



//    @GetMapping("/add")
//    public String add() {
//
//        Publisher publisher = new Publisher();
//        publisher.setName("PWN");
//        publisherDao.save(publisher);
//
//        Book book = new Book();
//        book.setTitle("Thinking in Java");
//        book.setPublisher(publisher);
//
//
//        bookRepository.save(book);
//
//        Book byId = bookDao.findById(1);
//        System.out.println(byId.getId());
//        byId.setRatingBook(12);
//        bookDao.update(byId);
//        return "ok";
//    }
//
//    @GetMapping("/get/{id}")
//    public Book getBook(@PathVariable Long id) {
//        return bookDao.findById(id);
//    }
//
//    @GetMapping("/get-new")
//    public Book getNewBook() {
//        return new Book();
//    }
//
//
//    @GetMapping("/all")
//    public List<Book> allBooks() {
//        return bookRepository.findAll();
//    }
