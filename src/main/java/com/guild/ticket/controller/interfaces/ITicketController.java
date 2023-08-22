package com.guild.ticket.controller.interfaces;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseObject;

import java.util.List;
import java.util.Set;

public interface ITicketController {
    public ResponseObject getAllTicket();

    public ResponseObject getTicketById(int id);

    public List<String> getTicketByShowTimeId(int show_time_id);

    public ResponseObject insertTicket(Ticket ticket);

    public ResponseObject updateTicket(int id, Ticket ticket);

    public ResponseObject removeTicket(int id);
}
