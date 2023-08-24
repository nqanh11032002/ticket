package com.guild.ticket.service.interfaces;

import com.guild.ticket.response.ResponseObject;

import java.time.LocalDate;
import java.util.Date;

public interface IStatisticService {
    public ResponseObject getStatisticRevenueTicketByMonth(String month);
}
