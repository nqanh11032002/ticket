package com.guild.ticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Payment payment;

    private int payment_id;

    @OneToOne
    @JoinColumn(name = "showTime_id", referencedColumnName = "id", insertable=false, updatable=false)
    private ShowTime showTime;

    private int showTime_id;

    @Column(length = 30)
    private String seatType;

    @Column(length = 10)
    private String seatId;

    private int numSeat;

    private double totalPrice;
}
