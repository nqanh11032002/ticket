package com.guild.movie.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CusResponseMessage {
    String emptyMoviesMess = "List of movies is empty";
    String existedMoviesMess = "List of movies has been found";
    String insertMovieSuccessMess = "Insert new movie successfully";
    String updateMovieSuccessMess = "Update movie successfully";
    String notFoundMovieMess = "Movie doesn't exist";
    String existedMovieMess = "Movie was founded";
    String deleteMovieSuccessMess = "Delete movie successfully";
}
