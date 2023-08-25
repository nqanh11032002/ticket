package com.guild.movie.controller.interfaces;

import com.guild.movie.dto.MovieDTO;
import com.guild.movie.response.ResponseObject;

public interface IMovieController {

    // API for ADMIN role
    public ResponseObject insertMovie(MovieDTO movieDTO);
    public ResponseObject listMoviesAdmin(Integer page, Integer size);
    public ResponseObject deleteMovie(Integer id);

    // API for USER role
    public ResponseObject listMoviesCustomer(Integer page, Integer size);
    public ResponseObject listMoviesComing(Integer page, Integer size);
    public ResponseObject listMovieShowing(Integer page, Integer size);

    // APi for both ADMIN and USER role
    public ResponseObject getMovie(Integer id);

}
