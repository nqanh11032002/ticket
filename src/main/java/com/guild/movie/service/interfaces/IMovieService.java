package com.guild.movie.service.interfaces;

import com.guild.movie.dto.MovieDTO;
import com.guild.movie.response.ResponseObject;

public interface IMovieService {
    public ResponseObject insertMovie(MovieDTO movieDTO);
    public ResponseObject listMoviesAdmin(Integer page, Integer size);
    public ResponseObject getMovie(Integer id);
    public ResponseObject deleteMovie(Integer id);
    public ResponseObject listMoviesCustomer(Integer page, Integer size);
    public ResponseObject listMoviesComing(Integer page, Integer size);
    public ResponseObject listMovieShowing(Integer page, Integer size);
}
