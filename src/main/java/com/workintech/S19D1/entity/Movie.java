package com.workintech.S19D1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie",schema = "fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank(message = "name alanı boş bırakılamaz!")
    private String name;

    @Column(name = "director_name")
    private String directorName;

    @Column(name = "rating")
    @Min(value = 0,message = "rating değeri 0 dan küçük olamaz")
    @Max(value = 10,message = "rating değeri 10 dan büyük olamaz")
    private Integer rating;

    @Column(name = "release_date")
    private String releaseDate;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "movie_actor",schema = "fsweb",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors=new ArrayList<>();

    public  void addActor(Actor actor){
        if (actor==null){
            actors=new ArrayList<>();
        }
        actors.add(actor);
    }

}
