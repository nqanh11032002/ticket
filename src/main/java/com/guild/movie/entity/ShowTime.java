package com.guild.movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "showtime")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String startTime;

    private String endTime;

    private String room;

    @Temporal(TemporalType.DATE)
    private Date dateShow;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_showtime",
            joinColumns = @JoinColumn(name = "showtimeId"),
            inverseJoinColumns = @JoinColumn(name = "movieId"))
    @JsonBackReference(value = "reference-time-movie")
    private List<Movie> movieList;
}
