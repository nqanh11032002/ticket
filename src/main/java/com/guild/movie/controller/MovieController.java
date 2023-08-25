package com.guild.movie.controller;

import com.guild.movie.controller.interfaces.IMovieController;
import com.guild.movie.dto.MovieDTO;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping ("/listMoviesAdmin")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject listMoviesAdmin(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return iMovieService.listMoviesAdmin(page, size);
    }

    @Override
    @GetMapping ("/getMovie")
    @PreAuthorize("hasRole('client_user')")
    public ResponseObject getMovie(@RequestParam("id") Integer id) {
        return iMovieService.getMovie(id);
    }

    @Override
    @DeleteMapping ("/deleteMovie")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject deleteMovie(@RequestParam("id") Integer id) {
        return iMovieService.deleteMovie(id);
    }

    @Override
    @GetMapping ("/listMoviesCustomer")
    @PreAuthorize("hasRole('client_user')")
    public ResponseObject listMoviesCustomer(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return iMovieService.listMoviesCustomer(page, size);
    }

    @Override
    @GetMapping ("/listMoviesComing")
    @PreAuthorize("hasRole('client_user')")
    public ResponseObject listMoviesComing(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return iMovieService.listMoviesComing(page, size);
    }

    @Override
    @GetMapping ("/listMoviesShowing")
    @PreAuthorize("hasRole('client_user')")
    public ResponseObject listMovieShowing(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return iMovieService.listMovieShowing(page, size);
    }

}
