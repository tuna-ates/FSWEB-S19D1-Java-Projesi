package com.workintech.S19D1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "actor",schema = "fsweb")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth_date")
    private String birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "movie_actor",schema = "fsweb",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies =new ArrayList<>();

    public  void addMovie(Movie movie){
        if (movie==null){
            movies=new ArrayList<>();
        }
        movies.add(movie);
    }
}
