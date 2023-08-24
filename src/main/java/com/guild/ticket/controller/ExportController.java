package com.guild.ticket.controller;

import com.guild.ticket.controller.interfaces.IExportController;
import com.guild.ticket.response.ResponseStatistic;
import com.guild.ticket.service.interfaces.IExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/export")
public class ExportController implements IExportController {
    @Autowired
    private IExportService exportService;

    @Override
    @GetMapping("/excel")
    @PreAuthorize("hasRole('client_admin')")
    public void exportToExcel(HttpServletResponse response, @RequestBody ResponseStatistic data) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        currentDateTime = currentDateTime.replace(":","_");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=statistic_" + currentDateTime +".xlsx";
        response.setHeader(headerKey, headerValue);

        data.setDate(LocalDate.parse(data.getDate().toString()));

        exportService.export(response, data);
    }
}
