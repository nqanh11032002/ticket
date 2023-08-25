package com.guild.movie.converter;

import com.guild.movie.dto.ShowTimeDTO;
import com.guild.movie.entity.Movie;
import com.guild.movie.entity.ShowTime;
import com.guild.movie.repository.IMovieRepository;
import com.guild.movie.repository.IShowTimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShowTimeDTOConverter {
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public ShowTimeDTO showTimeToShowTimeDTO(ShowTime showTime)
    {
        ShowTimeDTO showTimeDTO = modelMapper.map(showTime, ShowTimeDTO.class);

        return showTimeDTO;
    }

    public ShowTime showTimeDTOToShowTime(ShowTimeDTO showTimeDTO)
    {
        ShowTime showTime = modelMapper.map(showTimeDTO, ShowTime.class);

        return showTime;
    }
}
