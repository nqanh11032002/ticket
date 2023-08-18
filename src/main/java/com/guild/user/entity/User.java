package com.guild.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @UuidGenerator
    private UUID id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private int role_id;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Role role;
}
