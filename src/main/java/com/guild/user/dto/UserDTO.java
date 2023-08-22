package com.guild.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String role;
}
