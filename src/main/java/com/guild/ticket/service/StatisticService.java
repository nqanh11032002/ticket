package com.guild.ticket.service;

import com.guild.ticket.repository.IStatisticRepository;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.response.ResponseStatistic;
import com.guild.ticket.service.interfaces.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StatisticService implements IStatisticService {
    @Autowired
    private IStatisticRepository statisticRepository;

    @Override
    public ResponseObject getStatisticRevenueTicketByDay(int day) {
        try
        {
            ResponseStatistic statistic = statisticRepository.getStatisticRevenueTicketByDay(day);

            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Revenue of day: " + day)
                    .data(statistic)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found any result revenue of day: " + day)
                    .build();
        }
    }

    @Override
    public ResponseObject getStatisticRevenueTicketByMonth(int month) {
        try
        {
            ResponseStatistic statistic = statisticRepository.getStatisticRevenueTicketByMonth(month);

            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Revenue of month: " + month)
                    .data(statistic)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found any result revenue of month: " + month)
                    .build();
        }
    }

    @Override
    public ResponseObject getStatisticRevenueTicketByYear(int year) {
        try
        {
            ResponseStatistic statistic = statisticRepository.getStatisticRevenueTicketByYear(year);

            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Revenue of year: " + year)
                    .data(statistic)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found any result revenue of year: " + year)
                    .build();
        }
    }
}
