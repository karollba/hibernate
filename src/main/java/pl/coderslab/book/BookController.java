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

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/all")
    public List<Book> allBooks2() {
        return bookService.findAll();
    }

    // pobieranie po id
    @GetMapping("/get/{id}")
    public Book get(@PathVariable Long id){
        return bookService.findById(id);
    }


    @GetMapping("/add/{id}/{title}/{description}/{rating}/{category}")
    @ResponseBody
    public String add(@PathVariable String title,
                      @PathVariable String description,
                      @PathVariable int rating,
                      @PathVariable Category category) {

        Book book = new Book();
        book.setDescription(description);
        book.setRatingBook(rating);
        book.setTitle(title);
        book.setCategory(category);

        bookService.save(book);
        return "ok";
    }


    // edycja po id
    @GetMapping("/update/{id}/{title}/{description}/{rating}")
    public String update(@PathVariable Long id,
                         @PathVariable String title,
                         @PathVariable String description,
                         @PathVariable int rating){
        if(!bookService.exists(id)) {
            return "Not found id: " + id;
        }

        bookService.update(id, title, rating, description);
        return "zaktualizowano ksiazke o id: " + id;
    }

    // usuwanie po id
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
       if (!bookService.exists(id)) {
           return "nie znaleziono id: " + id;
       }

       bookService.delete(id);
       return "Usunieto ksiazke o id: " + id;
    }

}
