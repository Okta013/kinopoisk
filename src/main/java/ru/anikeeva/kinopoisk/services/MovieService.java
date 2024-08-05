package ru.anikeeva.kinopoisk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.anikeeva.kinopoisk.dto.GenreDTO;
import ru.anikeeva.kinopoisk.dto.MovieDTO;
import ru.anikeeva.kinopoisk.dto.ReviewDTO;
import ru.anikeeva.kinopoisk.entities.Movie;
import ru.anikeeva.kinopoisk.repositories.GenreRepository;
import ru.anikeeva.kinopoisk.repositories.MovieRepository;
import ru.anikeeva.kinopoisk.repositories.MovieSpecification;
import ru.anikeeva.kinopoisk.repositories.ReviewRepository;
import ru.anikeeva.kinopoisk.utils.MappingUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MappingUtils mappingUtils;
    private final ReviewRepository reviewRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, MappingUtils mappingUtils,
                        ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.mappingUtils = mappingUtils;
        this.reviewRepository = reviewRepository;
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = mappingUtils.mapToMovie(movieDTO);
        if (Objects.isNull(movie.getRating())) movie.setRating(5.0);
        movie = movieRepository.save(movie);
        return mappingUtils.mapToMovieDTO(movie);
    }

    public List<MovieDTO> getAllMovies(String name, Double rating, Integer premiered, Double minRating,
                                       Double maxRating, Integer startYear, Integer endYear) {
        if (Objects.isNull(name) && Objects.isNull(rating) && Objects.isNull(premiered)
                && Objects.isNull(minRating) && Objects.isNull(maxRating)
                && Objects.isNull(startYear) && Objects.isNull(endYear)) {
            return movieRepository.findAll().stream().map(mappingUtils::mapToMovieDTO)
                    .collect(Collectors.toList());
        }
        else {
            Specification<Movie> spec = Specification.where(MovieSpecification.hasName(name))
                    .and(MovieSpecification.hasRating(rating))
                    .and(MovieSpecification.hasPremiered(premiered))
                    .and(MovieSpecification.hasRatingBetween(minRating, maxRating))
                    .and(MovieSpecification.isPremieredBetween(startYear, endYear));
            return movieRepository.findAll(spec).stream().map(mappingUtils::mapToMovieDTO)
                    .collect(Collectors.toList());
        }
    }

//    public Page<MovieDTO> getAllMovies(String criteria, String sortDirection, Pageable pageable) {
//        if (Objects.isNull(criteria)) return movieRepository.findAll(pageable)
//        .map(mappingUtils::mapToMovieDTO);
//        else {
//            Pageable sortedPageable = null;
//            if (criteria.equals("name")) {
//                if (sortDirection.equals("asc"))
//                    sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//                            Sort.by("name").ascending());
//                else sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//                        Sort.by("name").descending());
//            } else if (criteria.equals("rating")) {
//                if (sortDirection.equals("asc"))
//                    sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//                            Sort.by("rating").ascending());
//                else sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//                        Sort.by("rating").descending());
//            } else if (criteria.equals("premiered")) {
//                if (sortDirection.equals("asc"))
//                    sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//                            Sort.by("premiered").ascending());
//                else sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//                        Sort.by("premiered").descending());
//            }
//            return movieRepository.findAll(sortedPageable).map(mappingUtils::mapToMovieDTO);
//            }
//        }


    public MovieDTO getMovieById(int id) {
        return mappingUtils.mapToMovieDTO(movieRepository.findById(id).orElse(null));
    }

    public MovieDTO updateMovie(int id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setRating(movieDTO.getRating());
        movieRepository.save(movie);
        return mappingUtils.mapToMovieDTO(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    public List<ReviewDTO> getReviews(int id) {
        Movie movie = mappingUtils.mapToMovie(getMovieById(id));
        return reviewRepository.findByMovie(movie)
                .stream().map(mappingUtils::mapToReviewDTO).collect(Collectors.toList());
    }

    public Page<MovieDTO> searchMovies(String name, Integer premiered, Double rating, String sortDirection, Pageable pageable) {
        if (Optional.ofNullable(name).isPresent() && sortDirection.equals("asc")) {
            return movieRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name, pageable).map(mappingUtils::mapToMovieDTO);
        }
        else if (Optional.ofNullable(name).isPresent() && sortDirection.equals("desc")){
            return movieRepository.findByNameContainingIgnoreCaseOrderByNameDesc(name, pageable).map(mappingUtils::mapToMovieDTO);
        }
        else if (Optional.ofNullable(premiered).isPresent() && sortDirection.equals("asc")) {
            return movieRepository.findByPremieredOrderByNameAsc(premiered, pageable).map(mappingUtils::mapToMovieDTO);
        }
        else if (Optional.ofNullable(premiered).isPresent() && sortDirection.equals("desc")) {
            return movieRepository.findByPremieredOrderByNameDesc(premiered, pageable).map(mappingUtils::mapToMovieDTO);
        }
        else if (Optional.ofNullable(rating).isPresent() && sortDirection.equals("asc")) {
            return movieRepository.findByRatingOrderByNameAsc(rating, pageable).map(mappingUtils::mapToMovieDTO);
        }
        else {
            return movieRepository.findByRatingOrderByNameDesc(rating, pageable).map(mappingUtils::mapToMovieDTO);
        }
    }
}
