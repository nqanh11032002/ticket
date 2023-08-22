package com.guild.movie.controller.interfaces;

import com.guild.movie.dto.MovieDTO;
import com.guild.movie.response.ResponseObject;
import org.springframework.web.bind.annotation.RequestBody;

public interface IMovieController {
    public ResponseObject insertMovie(MovieDTO movieDTO);
    public ResponseObject getAllMovies();
    public ResponseObject getMovie(Integer id);
    public ResponseObject deleteMovie(Integer id);
    public ResponseObject updateMovie(Integer id, MovieDTO movieDTO);

}
