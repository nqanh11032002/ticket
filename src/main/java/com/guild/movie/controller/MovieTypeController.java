package com.guild.movie.controller;

import com.guild.movie.controller.interfaces.IMovieTypeController;
import com.guild.movie.dto.MovieTypeDTO;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IMovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movieType")
public class MovieTypeController implements IMovieTypeController {
    @Autowired
    private IMovieTypeService iMovieTypeService;
    @Override
    @PostMapping("/insertMovieType")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject insertMovieType(@RequestBody MovieTypeDTO movieTypeDTO) {
        return iMovieTypeService.insertMovieType(movieTypeDTO);
    }

    @Override
    @GetMapping("/listMovieTypes")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject listMovieTypes() {
        return iMovieTypeService.listMoviesType();
    }

    @Override
    @GetMapping("/getMovieType")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getMovieType(@RequestParam("id") Integer id) {
        return iMovieTypeService.getMovieType(id);
    }

    @Override
    @DeleteMapping("/deleteMovieType")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject deleteMovieType(@RequestParam("id") Integer id) {
        return iMovieTypeService.deleteMovieType(id);
    }
}
