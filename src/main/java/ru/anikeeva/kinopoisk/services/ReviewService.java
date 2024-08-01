package ru.anikeeva.kinopoisk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.entities.Movie;
import ru.anikeeva.kinopoisk.entities.Review;
import ru.anikeeva.kinopoisk.repositories.MovieRepository;
import ru.anikeeva.kinopoisk.repositories.ReviewRepository;
import ru.anikeeva.kinopoisk.utils.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MappingUtils mappingUtils;
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MappingUtils mappingUtils, MovieRepository movieRepository, MovieService movieService) {
        this.reviewRepository = reviewRepository;
        this.mappingUtils = mappingUtils;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO, int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()
                -> new IllegalArgumentException("Movie not found"));
        List<Double> assessments = reviewRepository.findByMovie(movie)
                .stream().map(Review::getAssessment).collect(Collectors.toList());
        Review review = mappingUtils.mapToReview(reviewDTO);
        review = reviewRepository.save(review);
        assessments.add(review.getAssessment());
        OptionalDouble updatedRating = assessments.stream()
                .mapToDouble(Double::doubleValue)
                .average();
        MovieDTO movieDTO = mappingUtils.mapToMovieDTO(movie);
        movieDTO.setRating(updatedRating.getAsDouble());
        movieService.updateMovie(movieId, movieDTO);
        return mappingUtils.mapToReviewDTO(review);
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream().map(mappingUtils::mapToReviewDTO).collect(Collectors.toList());
    }

    public ReviewDTO getReviewById(int id) {
        return mappingUtils.mapToReviewDTO(reviewRepository.findById(id).orElse(null));
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
