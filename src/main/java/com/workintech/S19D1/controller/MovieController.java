package com.workintech.S19D1.controller;

import com.workintech.S19D1.dto.MovieRequest;
import com.workintech.S19D1.dto.MovieResponse;
import com.workintech.S19D1.entity.Actor;
import com.workintech.S19D1.entity.Movie;
import com.workintech.S19D1.service.MovieService;
import com.workintech.S19D1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workintech/movies")
@Validated
public class MovieController {
    private MovieService movieService;

@Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/")
    public MovieResponse save( @RequestBody MovieRequest movieRequest){
        Movie movie=movieRequest.getMovie();
        List<Actor> actors=movieRequest.getActors();
        for (Actor actor:actors){
            movie.addActor(actor);
        }
        movieService.save(movie);
        return Converter.movieResponseConvert(movie);
    }
    @GetMapping("/")
    public List<MovieResponse> findAll(){
        List<Movie> movies=movieService.findAll();
      return Converter.movieResponseConvert(movies);

    }
    @GetMapping("/{id}")
    public MovieResponse finById(@PathVariable long id){
       Movie movie=movieService.findById(id);
       return  Converter.movieResponseConvert(movie);
    }

    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable long id,@Validated @RequestBody Movie movie){
       Movie movieSaved=movieService.findById(id);
       movieSaved.setActors(movie.getActors());
       movieSaved.setId(movie.getId());
       movieService.save(movie);
       return Converter.movieResponseConvert(movieSaved);

    }
    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable long id){
    Movie movie=movieService.findById(id);
       movieService.delete(id);
       return Converter.movieResponseConvert(movie);
    }

}
