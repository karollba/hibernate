package pl.coderslab.movie;

import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

//    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieService movieService;

    public MovieController(GenreRepository genreRepository, MovieService movieService) {
//        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieService = movieService;
    }


    @GetMapping("/get/{id}")
    public MovieDTO get(@PathVariable Long id) {
      return movieService.findById(id);
    }

    // genres
    @GetMapping("/addgenre/{name}")
    public String addGenre(@PathVariable String name) {
        Genre genre = new Genre();
        genre.setName(name);
        genreRepository.save(genre);
        return "ok";
    }

    @GetMapping("/addmovie/{title}/{year}/{genre}/{rating}")
    public String addGenreToMovie(@PathVariable String title,
                                  @PathVariable int year,
                                  @PathVariable String genre,
                                  @PathVariable int rating) {

        Genre genreEntity = genreRepository.findByName(genre);

        if (genre == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setReleaseYear(year);
        movie.setRating(rating);
        movie.getGenres().add(genreEntity);

        movieService.save(movie);
        return "ok";
    }

    @GetMapping("/all")
    public List<MovieDTO> all() {
       return movieService.findAll();
    }



    @GetMapping("/bygenre/{id}")
    public List<MovieDTO> byGenreId(@PathVariable Long id) {
      return movieService.findByGenre(id);
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!movieService.exists(id)) {
            return "Nie znaleziono filmu o id: " + id;
        }
        movieService.delete(id);
        return "Usunieto film o id: " + id;
    }

}