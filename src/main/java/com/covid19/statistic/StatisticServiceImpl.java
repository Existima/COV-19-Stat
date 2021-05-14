package com.covid19.statistic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class StatisticServiceImpl {
    private static final Logger LOGGER = Logger.getLogger("Main");
    private static final String EXCEL_FILE_NAME = "Statistic.xlsx";
    private static final String URL = "https://api.apify.com/v2/datasets/L3VCmhMeX0KUQeJto/items?format=json&clean=1&fbclid=IwAR1ZEcY32PT-6ctGcL4XfVF1D2Sfe3G6WAqwvmdwP2a8hZ9aw3dVbbiBl8E";
    private static final String ADDRESSES_FILE_NAME = "address.txt";

    private final CovidService covidService;
    private final ExcelService excelService;
    private final MailService mailService;
    private final EmailFileReader emailFileReader;

    public StatisticServiceImpl(CovidService covidService, ExcelService excelService, MailService mailService, EmailFileReader emailFileReader) {
        this.covidService = covidService;
        this.excelService = excelService;
        this.mailService = mailService;
        this.emailFileReader = emailFileReader;
    }

    public void processStatistics() {
        CovidService reader = new CovidService();

        URL url = null;

        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            LOGGER.severe(String.format("URL %s is Malformed", URL));
            System.exit(1);
        }

        Statistic[] statistic = reader.getStatistics(url);
        Path filePath = Paths.get(".").resolve(EXCEL_FILE_NAME);

        ExcelService excelService = new ExcelService();

        try {
            excelService.createExcelFile(filePath, statistic);
        } catch (IOException e) {
            LOGGER.severe(String.format("Can't create file: %s", filePath));
            System.exit(1);
        }

        covidService.getStatisticForYesterday(url).ifPresent(yesterdayStat ->
                mailService.sendMail(emailFileReader.getEmails(ADDRESSES_FILE_NAME), yesterdayStat, filePath)
        );
    }
}
