package com.guild.ticket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable=false, updatable=false)
    @JsonBackReference(value = "reference-user-payment")
    private User user;

    @Column(length = 35)
    private String username;

    @Column(length = 90)
    private String paymentMethod;

    private double totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @OneToOne(mappedBy = "payment")
    @JsonBackReference(value="reference-payment")
    private Ticket ticket;

    private Boolean status;
}
