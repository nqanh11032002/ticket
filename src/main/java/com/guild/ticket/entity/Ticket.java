package com.guild.ticket.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonManagedReference(value="reference-payment")
    private Payment payment;

    private int payment_id;

    @OneToOne
    @JoinColumn(name = "showTime_id", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonManagedReference(value="reference-showtime")
    private ShowTime showTime;

    private int showTime_id;

    private String seat;

    private int numSeat;
}
