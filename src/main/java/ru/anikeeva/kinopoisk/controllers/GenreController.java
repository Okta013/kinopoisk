package ru.anikeeva.kinopoisk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anikeeva.kinopoisk.dto.GenreDTO;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.services.GenreService;

import java.util.List;

@Tag(name = "Жанры", description = "CRUD-операции с жанрами")
@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Operation(
            summary = "Все жанры",
            description = "Позволяет получить список всех жанров"
    )
    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @Operation(
            summary = "Просмотр жанра",
            description = "Позволяет прочитать информацию о конкретном жанре по id"
    )
    @GetMapping("/{id}")
    public GenreDTO getGenreById(@PathVariable int id) {
        return genreService.getGenreById(id);
    }

    @Operation(
            summary = "Создание жанра",
            description = "Позволяет создать новый жанр"
    )
    @PostMapping("/new")
    public GenreDTO createGenre(@RequestBody GenreDTO genreDTO) {
        return genreService.createGenre(genreDTO);
    }

    @Operation(
            summary = "Редактирование жанра",
            description = "Позволяет отредактировать поля существующего жанра по id"
    )
    @PutMapping("/{id}")
    public GenreDTO updateGenre(@PathVariable int id, @RequestBody GenreDTO genreDTO) {
        return genreService.updateGenre(id, genreDTO);
    }

    @Operation(
            summary = "Удаление жанра",
            description = "Позволяет удалить существующий жанр по id"
    )
    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable int id) {
        genreService.deleteGenre(id);
    }

    @Operation(
            summary = "Получение списка фильмов",
            description = "Позволяет получить список всех фильмов конкретного жанра по id"
    )
    @GetMapping("/{id}/movies")
    public List<MovieDTO> getMoviesGenres(@PathVariable("id") int genreId) {
        return genreService.getAllMoviesByGenre(genreId);
    }
}
