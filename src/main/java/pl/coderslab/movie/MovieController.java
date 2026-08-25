package pl.coderslab.movie;

import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieController(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
        movie.setId(id);
        return movieRepository.save(movie);
    }


    // genres
    @GetMapping("/addgenre/{name}")
    public String addGenre(@PathVariable String name) {
        Genre genre = new Genre();
        genre.setName(name);
        genreRepository.save(genre);
        return "ok";
    }

    @GetMapping("/addgenretomovie/{title}/{year}")
    public String addGenreToMovie(@PathVariable String title,
                                  @PathVariable int year) {

        Genre genre1 = genreRepository.findById(1L).orElse(null);
        Genre genre2 = genreRepository.findById(2L).orElse(null);

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setReleaseYear(year);

        movieRepository.save(movie);
        return "ok";
    }

    @GetMapping("/all")
    public List<Movie> all() {
        return movieRepository.findAll();
    }

    @GetMapping("/bygenre/{id}")
    public List<Movie> byGenreId(@PathVariable Long id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        return movieRepository.findMoviesByGenres(genre);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!movieRepository.existsById(id)) {
            return "nie znaleziono movie o id " +  id;
        }
        movieRepository.deleteById(id);
        return "Usunieto film o id " + id;
    }

}