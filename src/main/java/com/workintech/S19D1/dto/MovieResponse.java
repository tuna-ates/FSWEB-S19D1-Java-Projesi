package com.workintech.S19D1.dto;

import com.workintech.S19D1.entity.Actor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public record MovieResponse(Long id, String name, String directorName, Integer rating, String releaseDate, List<Actor> actors) {
}
