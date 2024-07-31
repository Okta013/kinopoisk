package ru.anikeeva.kinopoisk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anikeeva.kinopoisk.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
