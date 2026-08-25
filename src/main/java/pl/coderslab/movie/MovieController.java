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

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieController(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }


    @GetMapping("/get/{id}")
    public MovieDTO get(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return null;
        }
        return new MovieDTO(movie);
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

        movieRepository.save(movie);
        return "ok";
    }

    @GetMapping("/all")
    public List<MovieDTO> all() {
        return movieRepository.findAll()
                .stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }



    @GetMapping("/bygenre/{id}")
    public List<MovieDTO> byGenreId(@PathVariable Long id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        return movieRepository.findMoviesByGenres(genre)
                .stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
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