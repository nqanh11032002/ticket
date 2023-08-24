package com.guild.ticket.service;

import com.guild.ticket.response.ResponseStatistic;
import com.guild.ticket.service.interfaces.IExportService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;


@Service
public class ExportService implements IExportService {
    private static final String sheetName = "sheet_1";
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExportService() {
        workbook = new XSSFWorkbook();
    }


    @Override
    public void writeHeaderLine() {

        sheet = workbook.getSheet(sheetName);

        //check name sheet is exists
        if(sheet == null)
        {
            sheet = workbook.createSheet(sheetName);
        }

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Date", style);
        createCell(row, 1, "Ticket", style);
        createCell(row, 2, "Total price", style);
    }

    @Override
    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (value instanceof LocalDate) {
            cell.setCellValue(String.valueOf((LocalDate) value));
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    @Override
    public void writeDataLines(ResponseStatistic data) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        Row row = sheet.createRow(rowCount++);
        int columnCount = 0;

        createCell(row, columnCount++, data.getDate(), style);
        createCell(row, columnCount++, data.getTicket(), style);
        createCell(row, columnCount++, data.getTotalPrice(), style);
    }

    @Override
    public void export(HttpServletResponse response, ResponseStatistic data) throws IOException {
        try
        {
            writeHeaderLine();
            writeDataLines(data);

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
//            workbook.close();
//
//            outputStream.close();
        }catch (Exception e){

        }
    }
}
