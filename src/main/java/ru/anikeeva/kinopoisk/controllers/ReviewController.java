package ru.anikeeva.kinopoisk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.services.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping("/new")
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO, @RequestParam int movieId) {
        return reviewService.createReview(reviewDTO, movieId);
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(id, reviewDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
    }
}
