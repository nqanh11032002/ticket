package com.guild.ticket.repository;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IStatisticRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT new com.guild.ticket.response.ResponseStatistic(day (t.created_at), count(t.id), sum(p.totalPrice)) FROM Ticket as t join Payment as p on t.payment_id = p.id WHERE day(t.created_at) = :day")
    public ResponseStatistic getStatisticRevenueTicketByDay(@Param("day") int day);
    @Query("SELECT new com.guild.ticket.response.ResponseStatistic(month(t.created_at), count(t.id), sum(p.totalPrice)) FROM Ticket as t join Payment as p on t.payment_id = p.id WHERE month(t.created_at) = :month")
    public ResponseStatistic getStatisticRevenueTicketByMonth(@Param("month") int month);
    @Query("SELECT new com.guild.ticket.response.ResponseStatistic(year (t.created_at), count(t.id), sum(p.totalPrice)) FROM Ticket as t join Payment as p on t.payment_id = p.id WHERE year(t.created_at) = :year")
    public ResponseStatistic getStatisticRevenueTicketByYear(@Param("year") int month);
}
