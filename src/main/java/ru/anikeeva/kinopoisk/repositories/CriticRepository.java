package ru.anikeeva.kinopoisk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anikeeva.kinopoisk.entities.Critic;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Integer> {

}
