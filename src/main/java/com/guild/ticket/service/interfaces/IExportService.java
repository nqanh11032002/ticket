package com.guild.ticket.service.interfaces;

import com.guild.ticket.response.ResponseStatistic;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

public interface IExportService {
    public void writeHeaderLine();
    public  void createCell(Row row, int columnCount, Object value, CellStyle style);
    public void writeDataLines(ResponseStatistic data);
    public void export(HttpServletResponse response, ResponseStatistic data) throws IOException;
}
