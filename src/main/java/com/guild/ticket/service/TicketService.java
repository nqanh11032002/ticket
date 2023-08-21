package com.guild.ticket.service;

import com.guild.ticket.entity.Ticket;
import com.guild.ticket.repository.ITicketRepository;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    public ResponseObject getAllTicket() {
        List<Ticket> tickets = ticketRepository.findAll();

        if(tickets.size() == 0)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("List of tickets is empty")
                    .data(tickets).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("List of users has been found")
                .data(tickets).build();
    }

    @Override
    public ResponseObject getTicketById(int id) {
        var ticket = ticketRepository.findById(id);

        if(ticket.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found ticket by ID: " + id)
                    .data(ticket).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("Ticket found with ID: " + id)
                .data(ticket).build();
    }

    @Override
    public ResponseObject insertTicket(Ticket ticket) {
        ticketRepository.save(ticket);

        return ResponseObject.builder().status(HttpStatus.OK.name()).message("Insert new ticket successfully").data(ticket).build();
    }

    @Override
    public ResponseObject updateTicket(int id, Ticket ticket) {
        var ticketFound = ticketRepository.findById(id);

        if(ticketFound.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found ticket by ID: " + id + " to update")
                    .data(ticket).build();
        }

        Ticket ticketUpdate = ticketFound.get();
        ticketUpdate.setPayment_id(ticket.getPayment_id());
        ticketUpdate.setShowTime_id(ticket.getShowTime_id());
        ticketUpdate.setSeatType(ticket.getSeatType());
        ticketUpdate.setSeatId(ticket.getSeatType());
        ticketUpdate.setNumSeat(ticket.getNumSeat());
        ticketUpdate.setTotalPrice(ticket.getTotalPrice());

        ticketRepository.save(ticketUpdate);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("Update ticket by ID: " + id + " successfully")
                .data(ticket).build();
    }

    @Override
    public ResponseObject removeTicket(int id) {
        var ticketFound = ticketRepository.findById(id);

        if(ticketFound.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found ticket by ID: " + id + " to remove")
                    .data(null).build();
        }

        ticketRepository.deleteById(id);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("Remove ticket by ID: " + id + " successfully")
                .data(ticketFound).build();
    }
}
