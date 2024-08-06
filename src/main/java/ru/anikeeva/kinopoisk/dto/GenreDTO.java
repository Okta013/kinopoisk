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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        if (id != genreDTO.id) return false;
        if (name != null ? !name.equals(genreDTO.name) : genreDTO.name != null) return false;
        return description != null ? description.equals(genreDTO.description) : genreDTO.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
