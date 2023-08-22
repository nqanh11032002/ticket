package com.guild.ticket.repository;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
    public Optional findTicketByPayment_id(int payment);

    public List<Ticket> findTicketsByShowTime_id(int show_time_id);
}
