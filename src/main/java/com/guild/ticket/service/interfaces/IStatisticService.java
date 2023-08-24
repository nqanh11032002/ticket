package com.guild.ticket.service.interfaces;

import com.guild.ticket.response.ResponseObject;

import java.time.LocalDate;
import java.util.Date;

public interface IStatisticService {
    public ResponseObject getStatisticRevenueTicketByDay(int day);
    public ResponseObject getStatisticRevenueTicketByMonth(int month);
    public ResponseObject getStatisticRevenueTicketByYear(int year);
}
