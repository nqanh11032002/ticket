package com.guild.ticket.controller.interfaces;

import com.guild.ticket.response.ResponseObject;

public interface IStatisticController {
    public ResponseObject getStatisticRevenueTicketByDay(int day);
    public ResponseObject getStatisticRevenueTicketByMonth(int month);
    public ResponseObject getStatisticRevenueTicketByYear(int year);

}
