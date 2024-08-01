package ru.anikeeva.kinopoisk.utils;

import org.springframework.stereotype.Component;
import ru.anikeeva.kinopoisk.dto.CriticDTO;
import ru.anikeeva.kinopoisk.dto.GenreDTO;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.entities.Critic;
import ru.anikeeva.kinopoisk.entities.Genre;
import ru.anikeeva.kinopoisk.entities.Movie;
import ru.anikeeva.kinopoisk.entities.Review;

@Component
public class MappingUtils {
    public GenreDTO mapToGenreDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        return dto;
    }

    public Genre mapToGenre(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        return genre;
    }

    public MovieDTO mapToMovieDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setDescription(movie.getDescription());
        dto.setDuration(movie.getDuration());
        dto.setRating(movie.getRating());
        dto.setPremiered(movie.getPremiered());
        return dto;
    }

    public Movie mapToMovie(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setName(dto.getName());
        movie.setDescription(dto.getDescription());
        movie.setDuration(dto.getDuration());
        movie.setRating(dto.getRating());
        movie.setPremiered(dto.getPremiered());
        return movie;
    }

    public CriticDTO mapToCriticDTO(Critic critic) {
        CriticDTO dto = new CriticDTO();
        dto.setId(critic.getId());
        dto.setFirstName(critic.getFirstName());
        dto.setSecondName(critic.getSecondName());
        dto.setAbout(critic.getAbout());
        return dto;
    }

    public Critic mapToCritic(CriticDTO dto) {
        Critic critic = new Critic();
        critic.setId(dto.getId());
        critic.setFirstName(dto.getFirstName());
        critic.setSecondName(dto.getSecondName());
        critic.setAbout(dto.getAbout());
        return critic;
    }

    public ReviewDTO mapToReviewDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setAssessment(review.getAssessment());
        dto.setMessage(review.getMessage());
        dto.setDate(review.getDate());
        return dto;
    }

    public Review mapToReview(ReviewDTO dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setAssessment(dto.getAssessment());
        review.setMessage(dto.getMessage());
        review.setDate(dto.getDate());
        return review;
    }
}
