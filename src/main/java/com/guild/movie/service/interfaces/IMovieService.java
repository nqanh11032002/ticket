package com.guild.movie.service.interfaces;

import com.guild.movie.dto.MovieDTO;
import com.guild.movie.response.ResponseObject;

public interface IMovieService {
    public ResponseObject insertMovie(MovieDTO movieDTO);
    public ResponseObject getAllMovies();
    public ResponseObject getMovie(Integer id);
    public ResponseObject deleteMovie(Integer id);
    public ResponseObject updateMovie(Integer id, MovieDTO movieDTO);
}
