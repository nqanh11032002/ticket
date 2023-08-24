package com.guild.ticket.service;

import com.guild.ticket.repository.IStatisticRepository;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StatisticService implements IStatisticService {
    @Autowired
    private IStatisticRepository statisticRepository;

    @Override
    public ResponseObject getStatisticRevenueTicketByMonth(String month) {
        var statistic = statisticRepository.getStatisticRevenueTicketWithMonth(month);

        if(statistic == null)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found any result revenue of month: " + month)
                    .build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("Revenue of month: " + month)
                .data(statistic)
                .build();
    }
}
