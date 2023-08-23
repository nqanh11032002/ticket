package com.guild.ticket.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

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

    private int payment_id;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonManagedReference(value="reference-payment")
    private Payment payment;

    private int showTime_id;

    @OneToOne
    @JoinColumn(name = "showTime_id", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonManagedReference(value="reference-showtime")
    private ShowTime showTime;

    private String seat;

    private int numSeat;

    @CreationTimestamp
    private Date created_at;
}
