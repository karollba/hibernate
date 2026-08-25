package pl.coderslab.movie;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String title;
    private int releaseYear;
    private double rating;
    private List<Genre> genres = new ArrayList<>();

    public MovieDTO() {
    }

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
        this.genres = movie.getGenres();
        this.rating = movie.getRating();
    }
}
