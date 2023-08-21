package com.guild.ticket.entity;

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
    private Movie movie;

    private int room_id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Room room;

    @Column(length = 20)
    private String startTime;

    @Column(length = 20)
    private String endTime;

    private Date dateShow;

    @OneToOne(mappedBy = "showTime")
    private Ticket ticket;
}
