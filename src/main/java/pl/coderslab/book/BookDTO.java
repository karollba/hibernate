package pl.coderslab.book;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDTO {
    private Long id;
    private String title;
    private int ratingBook;
    private String description;

    public BookDTO(){}
}
