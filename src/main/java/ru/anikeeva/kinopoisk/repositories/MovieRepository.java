package ru.anikeeva.kinopoisk.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.anikeeva.kinopoisk.entities.Genre;
import ru.anikeeva.kinopoisk.entities.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {

    Page<Movie> findAll(Pageable pageable);

    Page<Movie> findAll(Specification<Movie> spec, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);

    Page<Movie> findByNameContainingIgnoreCaseOrderByNameDesc(String name, Pageable pageable);

    Page<Movie> findByPremieredOrderByNameAsc(int premiered, Pageable pageable);

    Page<Movie> findByPremieredOrderByNameDesc(int premiered, Pageable pageable);

    Page<Movie> findByRatingOrderByNameAsc(double rating, Pageable pageable);

    Page<Movie> findByRatingOrderByNameDesc(double rating, Pageable pageable);
}
