package com.guild.movie.dto;

import com.guild.movie.entity.MovieType;
import com.guild.movie.entity.ShowTime;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Integer id;

    private String name;

    private String title;

    private String urlImage;

    private String cast;

    private String director;

    private String description;

    private Integer duration;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Temporal(TemporalType.DATE)
    private Date finishDate;

    private Boolean status;

    private List<Integer> movieTypeIds;
}
