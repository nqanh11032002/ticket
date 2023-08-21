package com.guild.ticket.repository;

import com.guild.ticket.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
}
