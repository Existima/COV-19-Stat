package com.covid19.statistic;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadCell {

    public static void main(String[] args) throws IOException, ParseException, IllegalStateException {

        FileInputStream file = new FileInputStream(new File("Statistic.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2000-01-01");
        for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            for (int columnNum = 0; columnNum < row.getLastCellNum(); columnNum++) {
                Cell cell = row.getCell(columnNum);
                DateUtil.isCellDateFormatted(cell);
                if (DateUtil.isCellDateFormatted(cell)) {
                    if (date.compareTo(cell.getDateCellValue()) < 0) {
                        date = cell.getDateCellValue();
                    }
                }
            }
            System.out.println(date);
        }

    }
}