package com.guild.movie.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CusResponseMessage {
    // Movie message
    public static final String emptyMoviesMess = "List of movies is empty";
    public static final String existedMoviesMess = "List of movies has been found";
    public static final String insertMovieSuccessMess = "Insert new movie successfully";
    public static final String updateMovieSuccessMess = "Update movie successfully";
    public static final String notFoundMovieMess = "Movie doesn't exist";
    public static final String existedMovieMess = "Movie was founded";
    public static final String deleteMovieSuccessMess = "Delete movie successfully";

    // ShowTime message
    public static final String emptyShowTimesMess = "List of showTimes is empty";
    public static final String existedShowTimesMess = "List of showTimes has been found";
    public static final String insertShowTimeSuccessMess = "Insert new ShowTime successfully";
    public static final String deleteShowTimeSuccessMess = "Delete showTime successfully";
    public static final String notFoundShowTimeMess = "ShowTime doesn't exist";

    // MovieType message
    public static final String insertMovieTypeSuccessMess = "Insert new movieType successfully";
    public static final String emptyMovieTypesMess = "List of movieTypes is empty";
    public static final String existedMovieTypesMess = "List of movies has been found";
    public static final String notFoundMovieTypeMess = "MovieType doesn't exist";
    public static final String existedMovieTypeMess = "MovieType was founded";
    public static final String deleteMovieTypeSuccessMess = "Delete MovieType successfully";

}
