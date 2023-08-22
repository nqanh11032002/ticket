package com.guild.movie.controller;

import com.guild.movie.controller.interfaces.IMovieController;
import com.guild.movie.dto.MovieDTO;
import com.guild.movie.entity.Movie;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie")
public class MovieController implements IMovieController {
    @Autowired
    private IMovieService iMovieService;

    @Override
    @PostMapping("/insertMovie")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject insertMovie(@RequestBody MovieDTO movieDTO) {
        return iMovieService.insertMovie(movieDTO);
    }

    @Override
    public ResponseObject getAllMovies() {
        return null;
    }

    @Override
    public ResponseObject getMovie(Integer id) {
        return null;
    }

    @Override
    public ResponseObject deleteMovie(Integer id) {
        return null;
    }

    @Override
    public ResponseObject updateMovie(Integer id, MovieDTO movieDTO) {
        return null;
    }
}
