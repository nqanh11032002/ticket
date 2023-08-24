package com.guild.ticket.response;

import lombok.Data;

@Data
public class ResponseStatistic {
    private int data;

    private Long ticket;

    private double totalPrice;

    public ResponseStatistic(int data, Long ticket, double totalPrice) {
        this.data = data;
        this.ticket = ticket;
        this.totalPrice = totalPrice;
    }
}
