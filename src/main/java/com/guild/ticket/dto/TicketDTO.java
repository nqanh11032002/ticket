package com.guild.ticket.dto;

import lombok.Data;

@Data
public class TicketDTO {

    private int id;

    private int codePayment;

    private int showTime_id;

    private String seatType;

    private String seat;

    private int numSeat;

    private double totalPrice;
}
