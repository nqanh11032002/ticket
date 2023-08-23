package com.guild.ticket.controller;

import com.guild.ticket.controller.interfaces.ITicketController;
import com.guild.ticket.entity.Ticket;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ticket")
public class TicketController implements ITicketController {
    @Autowired
    private ITicketService ticketService;

    @Override
    @GetMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getAllTicket(@RequestParam("page") int page) {
        return ticketService.getAllTicket(page);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getTicketById(@PathVariable("id") int id) {
        return ticketService.getTicketById(id);
    }

    @Override
    @GetMapping("/seat/{show_time_id}")
    @PreAuthorize("hasRole('client_admin')")
    public List<String> getTicketByShowTimeId(@PathVariable("show_time_id") int show_time_id) {
        return ticketService.getTicketByShowTimeId(show_time_id);
    }

    @Override
    @PostMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject insertTicket(@RequestBody Ticket ticket) {
        return ticketService.insertTicket(ticket);
    }

    @Override
    @PutMapping ("/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject updateTicket(@PathVariable("id") int id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @Override
    @DeleteMapping ("/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject removeTicket(@PathVariable("id") int id) {
        return ticketService.removeTicket(id);
    }
}
