package ru.anikeeva.kinopoisk.utils;

import ru.anikeeva.kinopoisk.dto.CriticDTO;
import ru.anikeeva.kinopoisk.dto.GenreDTO;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.entities.Critic;
import ru.anikeeva.kinopoisk.entities.Genre;
import ru.anikeeva.kinopoisk.entities.Movie;
import ru.anikeeva.kinopoisk.entities.Review;

public class MappingUtils {
    public GenreDTO mapToClientDto(Genre genre){
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        return dto;
    }

    public Genre mapToClientEntity(GenreDTO dto){
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        return genre;
    }

    public MovieDTO mapToClientDto(Movie movie){
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setDescription(movie.getDescription());
        dto.setDuration(movie.getDuration());
        dto.setRating(movie.getRating());
        dto.setPremiered(movie.getPremiered());
        return dto;
    }

    public Movie mapToClientEntity(MovieDTO dto){
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setName(dto.getName());
        movie.setDescription(dto.getDescription());
        movie.setDuration(dto.getDuration());
        movie.setRating(dto.getRating());
        movie.setPremiered(dto.getPremiered());
        return movie;
    }

    public CriticDTO mapToClientDto(Critic critic){
        CriticDTO dto = new CriticDTO();
        dto.setId(critic.getId());
        dto.setFirstName(critic.getFirstName());
        dto.setSecondName(critic.getSecondName());
        dto.setAbout(critic.getAbout());
        return dto;
    }

    public Critic mapToClientEntity(CriticDTO dto){
        Critic critic = new Critic();
        critic.setId(dto.getId());
        critic.setFirstName(dto.getFirstName());
        critic.setSecondName(dto.getSecondName());
        critic.setAbout(dto.getAbout());
        return critic;
    }

    public ReviewDTO mapToClientDto(Review review){
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setAssessment(review.getAssessment());
        dto.setMessage(review.getMessage());
        dto.setDate(review.getDate());
        return dto;
    }

    public Review mapToClientEntity(ReviewDTO dto){
        Review review = new Review();
        review.setId(dto.getId());
        review.setAssessment(dto.getAssessment());
        review.setMessage(dto.getMessage());
        review.setDate(dto.getDate());
        return review;
    }
}
