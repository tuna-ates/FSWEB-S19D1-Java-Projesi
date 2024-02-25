package com.workintech.S19D1.dto;

import com.workintech.S19D1.entity.Movie;

import java.util.List;

public record ActorRespose(Long id, String firstName, String lastName, String gender, List<Movie> movies) {
}
