package com.guild.movie.repository;

import com.guild.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    public Optional findMovieById(Integer id);
}
