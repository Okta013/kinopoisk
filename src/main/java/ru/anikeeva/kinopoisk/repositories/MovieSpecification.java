package ru.anikeeva.kinopoisk.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ru.anikeeva.kinopoisk.entities.Movie;

import java.util.Optional;

public class MovieSpecification {

    public static Specification<Movie> hasRatingBetween(Double minRating, Double maxRating) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (Optional.ofNullable(minRating).isEmpty() && Optional.ofNullable(maxRating).isEmpty()) {
                return criteriaBuilder.conjunction();
            } else if (Optional.ofNullable(minRating).isEmpty()) {
                return criteriaBuilder.le(root.get("rating"), maxRating);
            } else if (Optional.ofNullable(maxRating).isEmpty()) {
                return criteriaBuilder.ge(root.get("rating"), minRating);
            }
            else return criteriaBuilder.between(root.get("rating"), minRating, maxRating);
        };
    }

    public static Specification<Movie> isPremieredBetween(Integer startYear, Integer endYear) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (Optional.ofNullable(startYear).isEmpty() && Optional.ofNullable(endYear).isEmpty()) {
                return criteriaBuilder.conjunction();
            } else if (Optional.ofNullable(startYear).isEmpty()) {
                return criteriaBuilder.le(root.get("premiered"), endYear);
            } else if (Optional.ofNullable(endYear).isEmpty()) {
                return criteriaBuilder.ge(root.get("premiered"), startYear);
            }
            else return criteriaBuilder.between(root.get("premiered"), startYear, endYear);
        };
    }
}
