package com.covid19.statistic;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {

        Workbook wb = new XSSFWorkbook();
        Sheet firstPage = wb.createSheet("First sheet");
        firstPage.setColumnWidth(0, 6000);
        firstPage.setColumnWidth(1, 4000);

        Row header = firstPage.createRow(0);

        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) wb).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Num of Infected");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Deceased");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Recovered");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Daily infected");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Daily tested");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("Daily positive tests");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(6);
        headerCell.setCellValue("Daily deceased");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(7);
        headerCell.setCellValue("Daily deceased due to COVID-19");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(8);
        headerCell.setCellValue("Daily recovered");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(9);
        headerCell.setCellValue("Daily Quarantine");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(10);
        headerCell.setCellValue("Date");
        headerCell.setCellStyle(headerStyle);

        JsonReader reader = new JsonReader();
        Statistic[] statistic = reader.readData();

        int rowNum = 0;
        for (Statistic stat : statistic) {

            if (stat.getInfected() == null || stat.getDeceased() == null || stat.getRecovered() == null
                    || stat.getDailyInfected() == null || stat.getDailyTested() == null || stat.getDailyPositiveTests() == null
                    || stat.getDailyDeceased() == null || stat.getDailyDeceasedDueToCovid() == null
                    || stat.getDailyRecovered() == null || stat.getDailyQuarantine() == null || stat.getDate() == null) {
                continue;
            }

            Row row = firstPage.createRow(rowNum + 1);

            Cell infectedCell = row.createCell(0);
            infectedCell.setCellValue(stat.getInfected());

            Cell deceasedCell = row.createCell(1);
            deceasedCell.setCellValue(stat.getDeceased());

            Cell recoveredCell = row.createCell(2);
            recoveredCell.setCellValue(stat.getDailyRecovered());

            Cell dailyInfectedCell = row.createCell(3);
            dailyInfectedCell.setCellValue(stat.getDailyInfected());

            Cell dailyTestedCell = row.createCell(4);
            dailyTestedCell.setCellValue(stat.getDailyTested());

            Cell dailyPositiveTests = row.createCell(5);
            dailyPositiveTests.setCellValue(stat.getDailyPositiveTests());

            Cell dailyDeceased = row.createCell(6);
            dailyDeceased.setCellValue(stat.getDailyDeceased());

            Cell dailyDeceasedDueToCovid = row.createCell(7);
            dailyDeceasedDueToCovid.setCellValue(stat.getDailyDeceasedDueToCovid());

            Cell dailyRecovered = row.createCell(8);
            dailyRecovered.setCellValue(stat.getDailyRecovered());

            Cell dailyQuarantine = row.createCell(9);
            dailyQuarantine.setCellValue(stat.getDailyQuarantine());

            Cell dateCell = row.createCell(10);
            dateCell.setCellValue(stat.getDate() + "");
            rowNum++;

        }

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLoc = path.substring(0, path.length() - 1) + "Statistic.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLoc);
        wb.write(outputStream);
        wb.close();



        ///iteration Statistic[] ,


        MailSend mailSend = new MailSend();
        try {
            mailSend.Sender();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
