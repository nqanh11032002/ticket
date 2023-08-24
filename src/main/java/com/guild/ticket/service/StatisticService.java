package com.guild.ticket.service;

import com.guild.ticket.repository.IStatisticRepository;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.response.ResponseStatistic;
import com.guild.ticket.service.interfaces.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatisticService implements IStatisticService {
    @Autowired
    private IStatisticRepository statisticRepository;

    @Override
    public ResponseObject getStatisticRevenueTicketByDate(String date) {
        try
        {
            ResponseStatistic statistic = statisticRepository.getStatisticRevenueTicketByDate(LocalDate.parse(date));

            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Revenue of date: " + date)
                    .data(statistic)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found any result revenue of date: " + date)
                    .build();
        }
    }

}
