package com.guild.movie.service;

import com.guild.movie.common.CusResponseMessage;
import com.guild.movie.converter.ShowTimeDTOConverter;
import com.guild.movie.dto.ShowTimeDTO;
import com.guild.movie.entity.Movie;
import com.guild.movie.entity.ShowTime;
import com.guild.movie.repository.IMovieRepository;
import com.guild.movie.repository.IShowTimeRepository;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowTimeService implements IShowTimeService {
    @Autowired
    private IShowTimeRepository iShowTimeRepository;
    @Autowired
    private IMovieRepository iMovieRepository;
    private final ShowTimeDTOConverter showTimeDTOConverter = new ShowTimeDTOConverter();

    @Override
    public ResponseObject insertShowTime(ShowTimeDTO showTimeDTO) {
        //Convert showTimeDTO to showTime
        ShowTime showTime = showTimeDTOConverter.showTimeDTOToShowTime(showTimeDTO);

        Optional<Movie> movie = iMovieRepository.findMovieById(showTimeDTO.getMovieId());
        if (movie.isEmpty())
            showTime.setMovie(null);
        else
            showTime.setMovie(movie.get());

        iShowTimeRepository.save(showTime);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.insertShowTimeSuccessMess)
                .data(showTime).build();
    }

    @Override
    public ResponseObject deleteShowTime(Integer id) {

        Optional<ShowTime> showTime = iShowTimeRepository.findShowTimeById(id);
        if (showTime.isEmpty())
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundShowTimeMess)
                    .data(null).build();

        iShowTimeRepository.delete(showTime.get());

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.deleteShowTimeSuccessMess)
                .data(null).build();
    }

    @Override
    public ResponseObject listShowTimeAdmin(String roomId, Date date) {

        List<ShowTime> showTimes = iShowTimeRepository.findAll();
        if (showTimes.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyShowTimesMess)
                    .data(null).build();
        }

        List<ShowTime> showTimesCustomer = new ArrayList<>();

        for (ShowTime showTime : showTimes)
        {
            if (Objects.equals(showTime.getRoomId(), roomId) && showTime.getDateShow().equals(date))
            {
                showTimesCustomer.add(showTime);
            }
        }
        if (showTimesCustomer.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyShowTimesMess)
                    .data(null).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedShowTimesMess)
                .data(showTimesCustomer).build();
    }

    @Override
    public ResponseObject listShowTimeCustomer(Integer movieId, Date date) {

        Optional<Movie> movie = iMovieRepository.findById(movieId);

        if (movie.isEmpty())
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundMovieMess)
                    .data(null).build();

        List<ShowTime> showTimeList = iShowTimeRepository.findShowTimesByMovieAndDateShow(movie.get(), date);
        List<ShowTimeDTO> showTimeDTOList = new ArrayList<>();

        for (ShowTime showTime : showTimeList)
        {
            showTimeDTOList.add(showTimeDTOConverter.showTimeToShowTimeDTO(showTime));
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedShowTimesMess)
                .data(showTimeDTOList).build();
    }
}
