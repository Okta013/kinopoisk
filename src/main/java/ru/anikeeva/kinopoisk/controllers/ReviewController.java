package ru.anikeeva.kinopoisk.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.services.ReviewService;

import java.util.List;

@Tag(name = "Рецензии", description = "CRUD-операции с рецензиями")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(
            summary = "Все рецензии",
            description = "Позволяет получить список всех рецензий"
    )
    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @Operation(
            summary = "Просмотр рецензии",
            description = "Позволяет прочитать информацию о конкретной рецензии по id"
    )
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @Operation(
            summary = "Создание рецензии",
            description = "Позволяет создать новую рецензию"
    )
    @PostMapping("/new")
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO, @RequestParam int movieId) {
        return reviewService.createReview(reviewDTO, movieId);
    }

    @Operation(
            summary = "Редактирование рецензии",
            description = "Позволяет отредактировать поля существующей рецензии по id"
    )
    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(id, reviewDTO);
    }

    @Operation(
            summary = "Удаление рецензии",
            description = "Позволяет удалить существующую рецензию по id"
    )
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
    }
}
