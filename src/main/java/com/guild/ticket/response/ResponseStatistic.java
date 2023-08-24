package com.guild.ticket.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ResponseStatistic {
    private LocalDate date;

    private Long ticket;

    private double totalPrice;

    public ResponseStatistic(LocalDate date, Long ticket, double totalPrice) {
        this.date = date;
        this.ticket = ticket;
        this.totalPrice = totalPrice;
    }
}
