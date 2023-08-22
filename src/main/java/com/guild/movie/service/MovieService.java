package com.guild.movie.service;

import com.guild.movie.common.CusResponseMessage;
import com.guild.movie.converter.MovieDTOConverter;
import com.guild.movie.dto.MovieDTO;
import com.guild.movie.entity.Movie;
import com.guild.movie.repository.IMovieRepository;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository iMovieRepository;

    private final CusResponseMessage cusResponseMessage = new CusResponseMessage();
    private final MovieDTOConverter movieDTOConverter = new MovieDTOConverter();

    @Override
    public ResponseObject insertMovie(MovieDTO movieDTO) {
        var movieFindById = iMovieRepository.findMovieById(movieDTO.getId());

        //Convert movie to movieDTO
        Movie movie = movieDTOConverter.movieDTOToMovie(movieDTO);

        iMovieRepository.save(movie);

        return ResponseObject.builder()
                .status(HttpStatus.CREATED.name())
                .message(cusResponseMessage.getInsertMovieSuccessMess())
                .data(movie).build();
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
