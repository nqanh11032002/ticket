package com.guild.ticket.service;

import com.guild.ticket.dto.TicketDTO;
import com.guild.ticket.entity.Ticket;
import com.guild.ticket.mapper.ITicketMapper;
import com.guild.ticket.repository.ITicketRepository;
import com.guild.ticket.response.ResponseMessage;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
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
    public ResponseObject getAllTicket(int page, int records) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, records, sort);

        List<Ticket> tickets = ticketRepository.findAll(pageRequest).getContent();

        if (tickets.size() == 0) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(ResponseMessage.getAllTicketNotFound)
                    .data(tickets).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(ResponseMessage.getAllTicket)
                .data(tickets).build();
    }

    @Override
    public ResponseObject getTicketByPaymentId(int payment_id) {
        var ticket = ticketRepository.findTicketByPayment_id(payment_id);

        if (ticket.isEmpty()) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(ResponseMessage.getTicketNotFound)
                    .data(ticket).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(ResponseMessage.getTicket)
                .data(ticket).build();
    }

    //Get seats booked
    @Override
    public List<String> getTicketByShowTimeId(int show_time_id) {
        List<Ticket> tickets = ticketRepository.findTicketsByShowTime_id(show_time_id);

        if (tickets.size() == 0) {
            return List.of(ResponseMessage.getAllTicketNotFound);
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
            return ResponseObject.builder().status(HttpStatus.CREATED.name())
                    .message(ResponseMessage.insertTicketFail).data(null).build();
        }

        ticketRepository.save(ticket);

        TicketDTO ticketDTO = ticketMapper.modelToDTO(ticket);
        return ResponseObject.builder().status(HttpStatus.OK.name())
                .message(ResponseMessage.insertTicket).data(ticketDTO).build();
    }

    @Override
    public ResponseObject removeTicket(int id) {
        var ticketFound = ticketRepository.findById(id);

        if (ticketFound.isEmpty()) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(ResponseMessage.removeTicketFail)
                    .data(null).build();
        }

        LocalDate dateNow = LocalDate.now();

        //convert type date to LocalDate
        Date dateTicket = ticketFound.get().getCreated_at();

        LocalDate localDate1 = dateTicket.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (dateNow.isAfter(localDate1.plusMonths(1))) {
            ticketRepository.deleteById(id);

            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(ResponseMessage.removeTicket)
                    .data(ticketFound).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(ResponseMessage.removePaymentFailRightNow)
                .data(null).build();

    }
}
