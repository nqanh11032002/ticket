package com.guild.ticket.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(length = 35)
    private String username;

    private String password;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(length = 13)
    private String phone;

    @Column(length = 60)
    private String email;

    @Column(length = 20)
    private String role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "reference-user-payment")
    private List<Payment> payments;
}