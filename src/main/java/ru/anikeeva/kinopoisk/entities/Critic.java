package ru.anikeeva.kinopoisk.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="critic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Critic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="second_name")
    private String secondName;

    @Column(name="about")
    private String about;

    @OneToMany(mappedBy = "critic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> createdReviews = new ArrayList<>();
}
