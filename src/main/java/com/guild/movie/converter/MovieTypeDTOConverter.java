package com.guild.movie.converter;

import com.guild.movie.dto.MovieTypeDTO;
import com.guild.movie.entity.MovieType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieTypeDTOConverter {
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();
    public MovieTypeDTO movieTypeToMovieTypeDTO(MovieType movieType)
    {
        MovieTypeDTO movieTypeDTO = modelMapper.map(movieType, MovieTypeDTO.class);

        return movieTypeDTO;
    }

    public MovieType movieTypeDTOToMovieType(MovieTypeDTO movieTypeDTO)
    {
        MovieType movieType = modelMapper.map(movieTypeDTO, MovieType.class);

        return movieType;
    }
}
