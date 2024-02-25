package com.workintech.S19D1.repository;

import com.workintech.S19D1.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
