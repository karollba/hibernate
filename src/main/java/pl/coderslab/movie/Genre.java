package pl.coderslab.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

   @ManyToMany(mappedBy = "genres")
    private List<Movie> movies = new ArrayList<>();

}
