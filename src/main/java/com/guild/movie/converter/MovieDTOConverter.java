package com.guild.movie.converter;

import com.guild.movie.dto.MovieDTO;
import com.guild.movie.entity.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDTOConverter {
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();
    public MovieDTO movieToMovieDTO(Movie movie)
    {
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        return movieDTO;
    }

    public Movie movieDTOToMovie(MovieDTO movieDTO)
    {
        Movie movie = modelMapper.map(movieDTO, Movie.class);

        return movie;
    }
}
