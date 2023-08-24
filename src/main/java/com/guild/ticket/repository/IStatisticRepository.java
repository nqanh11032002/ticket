package com.guild.ticket.repository;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IStatisticRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT new com.guild.ticket.response.ResponseStatistic(month(t.created_at), count(t.id), sum(p.totalPrice))" +
            "FROM Ticket as t join Payment as p on t.payment_id = p.id WHERE :month = month(now())")
    public List<ResponseStatistic> getStatisticRevenueTicketWithMonth(@Param("month") String month);
}
