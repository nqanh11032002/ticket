package com.guild.movie.service.interfaces;

import com.guild.movie.dto.MovieTypeDTO;
import com.guild.movie.response.ResponseObject;

public interface IMovieTypeService {
    public ResponseObject insertMovieType (MovieTypeDTO movieTypeDTO);
    public ResponseObject listMoviesType ();
    public ResponseObject getMovieType (Integer id);
    public ResponseObject deleteMovieType (Integer id);
}
