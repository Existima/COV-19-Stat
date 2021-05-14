package com.covid19.statistic;

public class Main {

    public static void main(String[] args) {
        StatisticServiceFactory statisticServiceFactory = new StatisticServiceFactory(new MailServiceFactory());
        statisticServiceFactory.getStatisticService().processStatistics();
    }
}
