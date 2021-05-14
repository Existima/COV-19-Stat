package com.covid19.statistic;

public class StatisticServiceFactory {

    private final MailServiceFactory mailServiceFactory;

    public StatisticServiceFactory(MailServiceFactory mailServiceFactory) {
        this.mailServiceFactory = mailServiceFactory;
    }

    public StatisticServiceImpl getStatisticService(){
        CovidService covidService = new CovidService();
        ExcelService excelService = new ExcelService();
        EmailFileReader emailFileReader = new EmailFileReader();
        return new StatisticServiceImpl(covidService, excelService, mailServiceFactory.getMailService(), emailFileReader);
    }
}
