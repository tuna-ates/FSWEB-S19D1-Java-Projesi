package com.workintech.S19D1.util;

import com.workintech.S19D1.dto.ActorRespose;
import com.workintech.S19D1.dto.MovieResponse;
import com.workintech.S19D1.entity.Actor;
import com.workintech.S19D1.entity.Movie;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static ActorRespose actorResponseConvert(Actor actor) {
        return new ActorRespose(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getGender(),null);
    }


    public static List<ActorRespose> actorResponseConvert1(List<Actor> actors) {
        List<ActorRespose> actorResposes = new ArrayList<>();
        for (Actor actor:actors){
             actorResposes.add(new ActorRespose(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getGender(), null));
        }
        return actorResposes;
    }

    public static MovieResponse movieResponseConvert(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getName(), movie.getDirectorName(), movie.getRating(), movie.getReleaseDate(),null);
    }

    public static List<MovieResponse> movieResponseConvert(List<Movie> movies) {
        List<MovieResponse> movieResponses=new ArrayList<>();
        for (Movie movie:movies){

                movieResponses.add(new MovieResponse(movie.getId(),
                        movie.getName(), movie.getDirectorName(),
                        movie.getRating(), movie.getReleaseDate(), null));


        }
        return movieResponses;
    }
}
