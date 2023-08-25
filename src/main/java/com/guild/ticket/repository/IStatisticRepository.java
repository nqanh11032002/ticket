package com.guild.ticket.repository;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;


public interface IStatisticRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT new com.guild.ticket.response.ResponseStatistic(:date, count(t.id), sum(p.totalPrice)) FROM Ticket as t " +
            "join Payment as p on t.payment_id = p.id" +
            " WHERE day(t.created_at) = day(:date) and month(t.created_at) = month(:date) and year(t.created_at) = year(:date)")
    public ResponseStatistic getStatisticRevenueTicketByDate(@Param("date") LocalDate date);

}
