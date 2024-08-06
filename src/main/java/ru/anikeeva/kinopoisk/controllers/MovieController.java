package ru.anikeeva.kinopoisk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.services.MovieService;

import java.util.List;

@Tag(name = "Фильмы", description = "CRUD-операции с фильмами")
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(
            summary = "Все фильмы",
            description = "Позволяет получить список всех фильмов с возможностью фильтрации и/или сортировки"
    )
    @GetMapping
    public Page<MovieDTO> getMovies(@RequestParam(required = false) Double minRating,
                                    @RequestParam(required = false) Double maxRating,
                                    @RequestParam(required = false) Integer startYear,
                                    @RequestParam(required = false) Integer endYear,
                                    @RequestParam(defaultValue = "name") String sortCriteria,
                                    @RequestParam(defaultValue = "asc") String sortDirection,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        return movieService.getAllMovies(minRating, maxRating, startYear, endYear,
                sortCriteria, sortDirection, PageRequest.of(page, size));
    }

    @Operation(
            summary = "Просмотр фильма",
            description = "Позволяет прочитать информацию о конкретном фильме по id"
    )
    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @Operation(
            summary = "Создание фильма",
            description = "Позволяет создать новый фильм"
    )
    @PostMapping("/new")
    public MovieDTO createMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.createMovie(movieDTO);
    }

    @Operation(
            summary = "Редактирование фильма",
            description = "Позволяет отредактировать поля существующего фильма по id"
    )
    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable int id, @RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(id, movieDTO);
    }

    @Operation(
            summary = "Удаление фильма",
            description = "Позволяет удалить существующий фильм по id"
    )
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }

    @Operation(
            summary = "Получение рецензий",
            description = "Позволяет получить список рецензий по конкретному фильму через его id"
    )
    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getMovieReviews(@PathVariable int id) {
        return movieService.getReviews(id);
    }

    @Operation(
            summary = "Поиск фильма",
            description = "Позволяет найти фильм по указанным параметрам"
    )
    @GetMapping("/search")
    public Page<MovieDTO> getMovies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer premiered,
            @RequestParam(required = false) Double rating,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return movieService.searchMovies(name, premiered, rating, sortDirection, PageRequest.of(page, size));
    }
}
