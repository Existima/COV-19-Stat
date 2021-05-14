package com.covid19.statistic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;

public class CovidService {
    private static ObjectMapper mapper = new ObjectMapper();

    public CovidService() {
        mapper.registerModule(new JavaTimeModule());
    }

    public Statistic[] getStatistics(URL url) {
        try {
            return mapper.readValue(url, Statistic[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Statistic[0];
        }
    }

    public Optional<Statistic> getStatisticForYesterday(URL url) {
        Optional<Statistic> yesterdayStat = Optional.empty();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        for (Statistic stat : getStatistics(url)) {
            if (yesterday.compareTo(stat.getDate().toLocalDate()) == 0) {
                if (yesterdayStat.isPresent() && yesterdayStat.get().
                        getDate().isBefore(stat.getDate())) {
                    yesterdayStat = Optional.of(stat);
                } else if (yesterdayStat.isEmpty()) {
                    yesterdayStat = Optional.of(stat);
                }
            }
        }
        return yesterdayStat;
    }
}
