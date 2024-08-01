package ru.anikeeva.kinopoisk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.entities.Movie;
import ru.anikeeva.kinopoisk.repositories.MovieRepository;
import ru.anikeeva.kinopoisk.utils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public MovieService(MovieRepository movieRepository, MappingUtils mappingUtils) {
        this.movieRepository = movieRepository;
        this.mappingUtils = mappingUtils;
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = mappingUtils.mapToMovie(movieDTO);
        movie = movieRepository.save(movie);
        return mappingUtils.mapToMovieDTO(movie);
    }

    List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream().map(mappingUtils::mapToMovieDTO).collect(Collectors.toList());
    }

    public MovieDTO getMovieById(int id) {
        return mappingUtils.mapToMovieDTO(movieRepository.findById(id).orElse(new Movie()));
    }

    public MovieDTO updateMovie(int id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movieRepository.save(movie);
        return mappingUtils.mapToMovieDTO(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
}
