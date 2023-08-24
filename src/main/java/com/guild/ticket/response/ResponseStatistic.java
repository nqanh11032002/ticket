package com.guild.ticket.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class ResponseStatistic {
    private String date;

    private int ticket;

    private Long totalPrice;

    public ResponseStatistic(String date, int ticket, Long totalPrice) {
        this.date = date;
        this.ticket = ticket;
        this.totalPrice = totalPrice;
    }
}
