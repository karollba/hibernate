package pl.coderslab.movie;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }

    @Transactional
    public List<MovieDTO> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MovieDTO findById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) return null;

        return movieMapper.toDTO(movie);
    }

    @Transactional
    public List<MovieDTO> findByGenre(Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        return movieRepository.findMoviesByGenres(genre)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return movieRepository.existsById(id);
    }
}
