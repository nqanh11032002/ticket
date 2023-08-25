package com.guild.movie.repository;

import com.guild.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    public Optional<Movie> findMovieById(Integer id);
}
