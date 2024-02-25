package com.workintech.S19D1.dto;

import com.workintech.S19D1.entity.Actor;
import com.workintech.S19D1.entity.Movie;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Data

public class MovieRequest {
    private List<Actor> actors;

    private Movie movie;
}
