package com.guild.ticket.controller;

import com.guild.ticket.controller.interfaces.ITicketController;
import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ticket")
public class TicketController implements ITicketController {
    @Autowired
    private ITicketService ticketService;

    @Override
    @GetMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getAllTicket() {
        return ticketService.getAllTicket();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getTicketById(@PathVariable("id") int id) {
        return ticketService.getTicketById(id);
    }

    @Override
    @PostMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject insertTicket(@RequestBody Ticket ticket) {
        return ticketService.insertTicket(ticket);
    }

    @Override
    @PutMapping ("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject updateTicket(int id, Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @Override
    public ResponseObject removeTicket(int id) {
        return ticketService.removeTicket(id);
    }
}
