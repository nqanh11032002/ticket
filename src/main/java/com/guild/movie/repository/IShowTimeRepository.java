package com.guild.movie.repository;

import com.guild.movie.entity.Movie;
import com.guild.movie.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IShowTimeRepository extends JpaRepository<ShowTime, Integer> {
    public Optional<ShowTime> findShowTimeById (Integer id);
    public List<ShowTime> findShowTimesByMovieAndDateShow (Movie movieId, Date date);

}
