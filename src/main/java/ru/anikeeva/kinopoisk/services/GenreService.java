package ru.anikeeva.kinopoisk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikeeva.kinopoisk.dto.GenreDTO;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.entities.Genre;
import ru.anikeeva.kinopoisk.entities.Movie;
import ru.anikeeva.kinopoisk.repositories.GenreRepository;
import ru.anikeeva.kinopoisk.repositories.MovieRepository;
import ru.anikeeva.kinopoisk.utils.MappingUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final MappingUtils mappingUtils;
    private final MovieRepository movieRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository, MappingUtils mappingUtils,
                        MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.mappingUtils = mappingUtils;
        this.movieRepository = movieRepository;
    }

    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = mappingUtils.mapToGenre(genreDTO);
        genre = genreRepository.save(genre);
        return mappingUtils.mapToGenreDTO(genre);
    }

    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream().map(mappingUtils::mapToGenreDTO).collect(Collectors.toList());
    }

    public GenreDTO getGenreById(int id) {
        return mappingUtils.mapToGenreDTO(genreRepository.findById(id).orElse(null));
    }

    public GenreDTO updateGenre(int id, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("Genre not found"));
        genre.setName(genreDTO.getName());
        genre.setDescription(genreDTO.getDescription());
        genreRepository.save(genre);
        return mappingUtils.mapToGenreDTO(genre);
    }

    public void deleteGenre(int id) {
        genreRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMoviesByGenre(int genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() ->
                new RuntimeException("Genre not found"));
        List<Movie> movies = movieRepository.findByDeclaredGenresIn(Collections.singletonList(genre));
        return movies.stream().map(mappingUtils::mapToMovieDTO).collect(Collectors.toList());
    }
}
