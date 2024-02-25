package com.workintech.S19D1.service;

import com.workintech.S19D1.entity.Actor;
import com.workintech.S19D1.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie save(Movie movie);
    Movie findById(long id);

    List<Movie> findAll();
    Movie delete(long id);
}
