package com.guild.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_movieType",
            joinColumns = @JoinColumn(name = "movieTypeId"),
            inverseJoinColumns = @JoinColumn(name = "movieId"))
    private List<MovieType> movieTypes;

    @OneToMany(mappedBy = "movie")
    @JsonManagedReference(value = "reference-showTime-movie")
    @JsonIgnore
    private List<ShowTime> showTimes;
}
