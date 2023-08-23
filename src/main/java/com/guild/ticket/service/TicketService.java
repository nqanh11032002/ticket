package com.guild.ticket.service;

import com.guild.ticket.dto.TicketDTO;
import com.guild.ticket.entity.Ticket;
import com.guild.ticket.mapper.ITicketMapper;
import com.guild.ticket.repository.ITicketRepository;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.ITicketService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private ITicketMapper ticketMapper;


    @Override
    public ResponseObject getAllTicket(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, 2, sort);

        List<Ticket> tickets = ticketRepository.findAll(pageRequest).getContent();

        if (tickets.size() == 0) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("List of tickets is empty")
                    .data(tickets).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("List of tickets has been found")
                .data(tickets).build();
    }

    @Override
    public ResponseObject getTicketById(int id) {
        var ticket = ticketRepository.findById(id);

        if (ticket.isEmpty()) {
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

    //Get seats booked
    @Override
    public List<String> getTicketByShowTimeId(int show_time_id) {
        List<Ticket> tickets = ticketRepository.findTicketsByShowTime_id(show_time_id);

        if (tickets.size() == 0) {
            return List.of("List of tickets is empty");
        }

        String seats = tickets.stream()
                .map(Ticket::getSeat).collect(Collectors.toList()).toString();

        List<String> keysList = new ArrayList<>();
        Pattern pattern = Pattern.compile("'([^']+)':'[^']+'");

        Matcher matcher = pattern.matcher(seats);
        while (matcher.find()) {
            keysList.add(matcher.group(1));
        }

        return keysList;
    }

    @Override
    public ResponseObject insertTicket(Ticket ticket) {
        var ticketFound = ticketRepository.findTicketByPayment_id(ticket.getPayment_id());

        if (ticketFound.isPresent()) {
            return ResponseObject.builder().status(HttpStatus.OK.name())
                    .message("Transaction is existing").data(null).build();
        }

        ticketRepository.save(ticket);

        TicketDTO ticketDTO = ticketMapper.modelToDTO(ticket);
        return ResponseObject.builder().status(HttpStatus.OK.name())
                .message("Insert new ticket successfully").data(ticketDTO).build();
    }

    @Override
    public ResponseObject updateTicket(int id, Ticket ticket) {
        var ticketFound = ticketRepository.findById(id);

        if (ticketFound.isEmpty()) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found ticket by ID: " + id + " to update")
                    .data(ticket).build();
        }

        Ticket ticketUpdate = ticketFound.get();
        ticketUpdate.setPayment_id(ticket.getPayment_id());
        ticketUpdate.setShowTime_id(ticket.getShowTime_id());
        ticketUpdate.setSeat(ticket.getSeat());
        ticketUpdate.setNumSeat(ticket.getNumSeat());

        ticketRepository.save(ticketUpdate);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("Update ticket by ID: " + id + " successfully")
                .data(ticket).build();
    }

    @Override
    public ResponseObject removeTicket(int id) {
        var ticketFound = ticketRepository.findById(id);

        if (ticketFound.isEmpty()) {
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
