package com.guild.user.repository;

import com.guild.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer>{

    public Optional findByUserName(String userName);

    public Optional findByEmail(String email);

    public Optional findByPhone(String phone);
}
