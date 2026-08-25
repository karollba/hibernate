package pl.coderslab.movie;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public MovieDTO toDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setRating(movie.getRating());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setGenres(movie.getGenres()
                .stream()
                .map(Genre::getName)
                .collect(Collectors.toList()));
        return dto;
    }
}
