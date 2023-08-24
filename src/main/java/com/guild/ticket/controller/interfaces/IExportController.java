package com.guild.ticket.controller.interfaces;

import com.guild.ticket.response.ResponseStatistic;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

public interface IExportController {
    public void exportToExcel(HttpServletResponse response, ResponseStatistic data) throws IOException;
}
