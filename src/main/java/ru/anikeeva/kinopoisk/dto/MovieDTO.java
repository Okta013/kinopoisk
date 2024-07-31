package ru.anikeeva.kinopoisk.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.anikeeva.kinopoisk.entities.Genre;
import ru.anikeeva.kinopoisk.entities.Review;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, message = "Name should be longer than 2 symbols")
    private String name;

    @NotEmpty(message = "Description shouldn't be empty")
    @Size(min = 2, message = "Description should be longer than 2 symbols")
    private String description;

    @NotEmpty(message = "Duration shouldn't be empty")
    private LocalTime duration;

    private double rating;

    @NotEmpty(message = "Date of premiered shouldn't be empty")
    private LocalDate premiered;

    private List<Genre> declaredGenres = new ArrayList<>();

    private List<Review> writtenReviews = new ArrayList<>();

    public MovieDTO(String name, String description,
                    LocalTime duration, double rating, LocalDate premiered) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.rating = rating;
        this.premiered = premiered;
    }
}
