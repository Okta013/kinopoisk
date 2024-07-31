package ru.anikeeva.kinopoisk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anikeeva.kinopoisk.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
