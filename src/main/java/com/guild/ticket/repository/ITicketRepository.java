package com.guild.ticket.repository;

import com.guild.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
}
