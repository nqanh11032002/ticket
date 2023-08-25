package com.guild.movie.controller.interfaces;

import com.guild.movie.dto.MovieTypeDTO;
import com.guild.movie.response.ResponseObject;

public interface IMovieTypeController {
    // API for ADMIN role
    public ResponseObject insertMovieType (MovieTypeDTO movieTypeDTO);
    public ResponseObject listMovieTypes();
    public ResponseObject getMovieType (Integer id);
    public ResponseObject deleteMovieType (Integer id);
}
