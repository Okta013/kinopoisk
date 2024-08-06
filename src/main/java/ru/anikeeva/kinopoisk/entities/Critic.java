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

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Critic critic = (Critic) o;
        if (id != critic.id) return false;
        if (!firstName.equals(critic.firstName)) return false;
        if (!secondName.equals(critic.secondName)) return false;
        if (!about.equals(critic.about)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + about.hashCode();
        return result;
    }
}
