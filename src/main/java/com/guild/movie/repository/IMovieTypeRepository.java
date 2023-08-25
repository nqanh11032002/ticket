package com.guild.movie.repository;

import com.guild.movie.entity.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieTypeRepository extends JpaRepository<MovieType, Integer> {
    public Optional<MovieType> findMovieTypeById (Integer id);

}
