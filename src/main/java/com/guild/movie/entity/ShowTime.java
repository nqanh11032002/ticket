package com.guild.movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "showtime")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String startTime;

    private String endTime;

    private String roomId;

    @Temporal(TemporalType.DATE)
    private Date dateShow;

    @ManyToOne
    @JoinColumn(name = "movie_showTime")
    @JsonManagedReference(value = "reference-showTime-movie")
    private Movie movie;
}
