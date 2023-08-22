package com.guild.ticket.repository;

import com.guild.ticket.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
    public List<Payment> getPaymentByUsername(String username);
}
