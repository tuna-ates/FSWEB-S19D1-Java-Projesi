package com.workintech.S19D1.service;

import com.workintech.S19D1.entity.Actor;
import com.workintech.S19D1.entity.Movie;
import com.workintech.S19D1.exception.ApiException;
import com.workintech.S19D1.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{
    private MovieRepository movieRepository;
    private  ActorService actorService;
@Autowired
    public MovieServiceImpl(MovieRepository movieRepository, @Lazy ActorService actorService) {
        this.movieRepository = movieRepository;
        this.actorService = actorService;
    }

    @Override
    public Movie save(Movie movie) {

      return movieRepository.save(movie);
    }

    @Override
    public Movie findById(long id) {
        Optional<Movie> movieOptional=movieRepository.findById(id);
        if (!movieOptional.isPresent()){
            //TODO buraya error handler yazılıcak
            throw new ApiException("bu id de film yok!", HttpStatus.BAD_REQUEST);
        }
        return movieOptional.get();
    }

    @Override
    public List<Movie> findAll() {
    List<Movie> movies=new ArrayList<>();
         movieRepository.findAll().forEach(
                 movie -> movies.add(movie)
         );
        return movies;
    }

    @Override
    public Movie delete(long id) {
        Movie movie=findById(id);
        movieRepository.delete(movie);
        return movie;
    }
}
