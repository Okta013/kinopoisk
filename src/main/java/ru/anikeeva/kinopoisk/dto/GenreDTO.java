package ru.anikeeva.kinopoisk.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.anikeeva.kinopoisk.entities.Movie;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, message = "Name should be longer than 2 symbols")
    private String name;

    @NotEmpty(message = "Description shouldn't be empty")
    @Size(min = 2, message = "Description should be longer than 2 symbols")
    private String description;

    private List<Movie> suitableMovies = new ArrayList<>();

    public GenreDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
