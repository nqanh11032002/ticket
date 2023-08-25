package com.guild.movie.service;

import com.guild.movie.common.CusResponseMessage;
import com.guild.movie.converter.MovieTypeDTOConverter;
import com.guild.movie.dto.MovieTypeDTO;
import com.guild.movie.entity.MovieType;
import com.guild.movie.repository.IMovieTypeRepository;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IMovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieTypeService implements IMovieTypeService {
    @Autowired
    private IMovieTypeRepository iMovieTypeRepository;
    private final MovieTypeDTOConverter movieTypeDTOConverter = new MovieTypeDTOConverter();
    @Override
    public ResponseObject insertMovieType(MovieTypeDTO movieTypeDTO) {

        //Convert movieTypeDTO to movieType
        MovieType movieType = movieTypeDTOConverter.movieTypeDTOToMovieType(movieTypeDTO);

        iMovieTypeRepository.save(movieType);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.insertMovieTypeSuccessMess)
                .data(movieType).build();
    }

    @Override
    public ResponseObject listMoviesType() {

        List<MovieType> movieTypeList = iMovieTypeRepository.findAll();

        if (movieTypeList.isEmpty())
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyMovieTypesMess)
                    .data(null).build();

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedMovieTypesMess)
                .data(movieTypeList).build();
    }

    @Override
    public ResponseObject getMovieType(Integer id) {

        Optional<MovieType> movieType = iMovieTypeRepository.findMovieTypeById(id);

        if (movieType.isEmpty())
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundMovieTypeMess)
                    .data(movieType).build();

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedMovieTypeMess)
                .data(movieType).build();
    }

    @Override
    public ResponseObject deleteMovieType(Integer id) {

        Optional<MovieType> movieType = iMovieTypeRepository.findMovieTypeById(id);

        if (movieType.isEmpty())
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundMovieTypeMess)
                    .data(null).build();

        iMovieTypeRepository.delete(movieType.get());

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.deleteMovieTypeSuccessMess)
                .data(null).build();    }
}
