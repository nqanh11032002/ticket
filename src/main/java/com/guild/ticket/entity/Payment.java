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
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable=false, updatable=false)
    private User user;

    @Column(length = 35)
    private String username;

    @Column(length = 90)
    private String paymentMethod;

    private double totalPrice;

    private Date transactionDate;

    private int ticket_id;

    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Ticket ticket;
}
