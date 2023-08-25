package com.guild.movie.service;

import com.guild.movie.common.CusResponseMessage;
import com.guild.movie.converter.MovieDTOConverter;
import com.guild.movie.dto.MovieDTO;
import com.guild.movie.entity.Movie;
import com.guild.movie.entity.MovieType;
import com.guild.movie.repository.IMovieRepository;
import com.guild.movie.repository.IMovieTypeRepository;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository iMovieRepository;
    @Autowired
    private IMovieTypeRepository iMovieTypeRepository;
    private final MovieDTOConverter movieDTOConverter = new MovieDTOConverter();

    @Override
    public ResponseObject insertMovie(MovieDTO movieDTO) {

        //Convert movieDTO to movie
        Movie movie = movieDTOConverter.movieDTOToMovie(movieDTO);

        //Set list movieType for movie
        List<MovieType> movieTypes = new ArrayList<>();

        for (Integer movieTypeId : movieDTO.getMovieTypeIds())
        {
            Optional<MovieType> movieType = iMovieTypeRepository.findMovieTypeById(movieTypeId);
            if (movieType.isEmpty())
                continue;
            else
                movieTypes.add(movieType.get());
        }

        if (!movieTypes.isEmpty())
            movie.setMovieTypes(movieTypes);

        movie = iMovieRepository.save(movie);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.insertShowTimeSuccessMess)
                .data(movie).build();
    }

    @Override
    public ResponseObject listMoviesAdmin(Integer page, Integer size) {

        List<Movie> movieList = getListMovies(page, size);

        if (movieList == null)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyMoviesMess)
                    .data(null).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedMoviesMess)
                .data(movieList).build();
    }

    @Override
    public ResponseObject getMovie(Integer id) {

        Optional<Movie> movie = iMovieRepository.findMovieById(id);

        if (movie.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundMovieMess)
                    .data(null).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedMovieMess)
                .data(movie).build();
    }

    @Override
    public ResponseObject deleteMovie(Integer id) {

        Optional<Movie> movie = iMovieRepository.findMovieById(id);

        if (movie.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundMovieMess)
                    .data(null).build();
        }

        iMovieRepository.delete(movie.get());

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.deleteMovieSuccessMess)
                .data(null).build();
    }

    @Override
    public ResponseObject listMoviesCustomer(Integer page, Integer size) {

        List<Movie> movieList = getListMovies(page, size);

        if (movieList == null)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.existedMoviesMess)
                    .data(null).build();
        }

        List<Movie> movieListCustomer = new ArrayList<>();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();


        for (Movie movie : movieList)
        {
            if (movie.getStatus() && movie.getFinishDate().after(date))
            {
                movieListCustomer.add(movie);
            }
        }
        if (movieListCustomer.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyMoviesMess)
                    .data(null).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedMoviesMess)
                .data(movieListCustomer).build();

    }

    private List<Movie> getListMovies(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        List<Movie> movieList = iMovieRepository.findAll(pageRequest).getContent();

        if (movieList.isEmpty())
        {
            return null;
        }

        return  movieList;
    }

    @Override
    public ResponseObject listMoviesComing(Integer page, Integer size) {
        List<Movie> movieList = getListMovies(page, size);

        if (movieList == null)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyMoviesMess)
                    .data(null).build();
        }

        List<Movie> listMovieComing = new ArrayList<>();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();


        for (Movie movie : movieList)
        {
            if (movie.getStatus() && movie.getReleaseDate().after(date))
            {
                listMovieComing.add(movie);
            }
        }
        if (listMovieComing.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyMoviesMess)
                    .data(null).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedMoviesMess)
                .data(listMovieComing).build();
    }

    @Override
    public ResponseObject listMovieShowing(Integer page, Integer size) {
        List<Movie> movieList = getListMovies(page, size);

        if (movieList == null)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyMoviesMess)
                    .data(null).build();
        }

        List<Movie> listMovieComing = new ArrayList<>();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();


        for (Movie movie : movieList)
        {
            if (movie.getStatus() && movie.getReleaseDate().before(date) && movie.getFinishDate().after(date))
            {
                listMovieComing.add(movie);
            }
        }
        if (listMovieComing.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.emptyMoviesMess)
                    .data(null).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedMoviesMess)
                .data(listMovieComing).build();
    }

}
