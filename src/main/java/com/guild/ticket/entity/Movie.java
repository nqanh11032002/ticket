package com.guild.ticket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int movieType_id;

    @ManyToOne
    @JoinColumn(name = "movieType_id", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonBackReference(value = "reference-type-movie")
    private MovieType movieType;

    private String urlImage;

    private String director;

    private String cast;

    private String description;

    @Column(length = 50)
    private String duration;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Temporal(TemporalType.DATE)
    private Date finishDate;

    private Boolean status;

    @OneToMany(mappedBy = "movie")
    @JsonManagedReference(value = "reference-time-movie")
    private List<ShowTime> showTimes;
}
