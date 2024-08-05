package ru.anikeeva.kinopoisk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //@GetMapping
//    public Page<MovieDTO> getAllMovies(@RequestParam(required = false) String criteria,
//                                       @RequestParam(defaultValue = "asc") String sortDirection,
//                                       @RequestParam(defaultValue = "0") int page,
//                                       @RequestParam(defaultValue = "5") int size) {
//        return movieService.getAllMovies(criteria, sortDirection, PageRequest.of(page, size));
//    }

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

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/new")
    public MovieDTO createMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.createMovie(movieDTO);
    }

    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable int id, @RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(id, movieDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> getMovieReviews(@PathVariable int id) {
        return movieService.getReviews(id);
    }

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
