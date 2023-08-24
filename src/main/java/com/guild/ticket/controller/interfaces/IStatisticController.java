package com.guild.ticket.controller.interfaces;

import com.guild.ticket.response.ResponseObject;


public interface IStatisticController {
    public ResponseObject getStatisticRevenueTicketByDate(String day);

}
