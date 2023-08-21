package com.guild.ticket.controller.interfaces;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseObject;

public interface ITicketController {
    public ResponseObject getAllTicket();

    public ResponseObject getTicketById(int id);

    public ResponseObject insertTicket(Ticket ticket);

    public ResponseObject updateTicket(int id, Ticket ticket);

    public ResponseObject removeTicket(int id);
}
