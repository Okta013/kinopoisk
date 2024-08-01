package ru.anikeeva.kinopoisk.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.anikeeva.kinopoisk.entities.Review;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriticDTO {
    private int id;

    @NotEmpty(message = "First name shouldn't be empty")
    @Size(min = 2, message = "First name should be longer than 2 symbols")
    private String firstName;

    @NotEmpty(message = "Second name shouldn't be empty")
    @Size(min = 2, message = "Second name should be longer than 2 symbols")
    private String secondName;

    private String about;

    private List<Review> createdReviews = new ArrayList<>();

    public CriticDTO(String firstName, String secondName, String about) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.about = about;
    }
}
