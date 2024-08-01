package ru.anikeeva.kinopoisk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.entities.Review;
import ru.anikeeva.kinopoisk.repositories.ReviewRepository;
import ru.anikeeva.kinopoisk.utils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MappingUtils mappingUtils) {
        this.reviewRepository = reviewRepository;
        this.mappingUtils = mappingUtils;
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = mappingUtils.mapToReview(reviewDTO);
        review = reviewRepository.save(review);
        return mappingUtils.mapToReviewDTO(review);
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream().map(mappingUtils::mapToReviewDTO).collect(Collectors.toList());
    }

    public ReviewDTO getReviewById(int id) {
        return mappingUtils.mapToReviewDTO(reviewRepository.findById(id).orElse(new Review()));
    }

    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        review.setAssessment(reviewDTO.getAssessment());
        review.setMessage(reviewDTO.getMessage());
        reviewRepository.save(review);
        return mappingUtils.mapToReviewDTO(review);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
