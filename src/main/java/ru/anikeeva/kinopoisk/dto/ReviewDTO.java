package ru.anikeeva.kinopoisk.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.anikeeva.kinopoisk.entities.Critic;
import ru.anikeeva.kinopoisk.entities.Movie;
import ru.anikeeva.kinopoisk.entities.Review;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;

    @NotEmpty(message = "Assessment name shouldn't be empty")
    private double assessment;

    @NotEmpty(message = "Message shouldn't be empty")
    @Size(min = 2, message = "Message should be longer than 2 symbols")
    private String message;

    private LocalDate date;

    private Movie movie;

    private Critic critic;

    public ReviewDTO(double assessment, String message, LocalDate date) {
        this.assessment = assessment;
        this.message = message;
        this.date = date;
    }
}
