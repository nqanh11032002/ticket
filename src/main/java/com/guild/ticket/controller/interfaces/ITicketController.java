package com.guild.ticket.controller.interfaces;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseObject;

import java.util.List;
import java.util.Set;

public interface ITicketController {
    public ResponseObject getAllTicket(int page, int records);

    public ResponseObject getTicketByPaymentId(int payment_id);

    public List<String> getTicketByShowTimeId(int show_time_id);

    public ResponseObject insertTicket(Ticket ticket);

    public ResponseObject removeTicket(int id);
}
