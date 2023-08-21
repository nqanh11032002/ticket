package com.guild.ticket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "show_time")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int movie_id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonBackReference(value = "reference-time-movie")
    private Movie movie;

    private int room_id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonBackReference(value = "reference-time-room")
    private Room room;

    @Column(length = 20)
    private String startTime;

    @Column(length = 20)
    private String endTime;

    @Temporal(TemporalType.DATE)
    private Date dateShow;

    @OneToOne(mappedBy = "showTime")
    @JsonBackReference(value="reference-showtime")
    private Ticket ticket;
}
