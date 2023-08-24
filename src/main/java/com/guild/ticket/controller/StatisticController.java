package com.guild.ticket.controller;

import com.guild.ticket.controller.interfaces.IStatisticController;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController implements IStatisticController {
    @Autowired
    private IStatisticService statisticService;

    @Override
    @GetMapping("/revenue-ticket-day")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getStatisticRevenueTicketByDay(int day) {
        return statisticService.getStatisticRevenueTicketByDay(day);
    }

    @Override
    @GetMapping("/revenue-ticket-month")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getStatisticRevenueTicketByMonth(@RequestParam("month") int month) {
        return statisticService.getStatisticRevenueTicketByMonth(month);
    }

    @Override
    @GetMapping("/revenue-ticket-year")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getStatisticRevenueTicketByYear(int year) {
        return statisticService.getStatisticRevenueTicketByYear(year);
    }
}
