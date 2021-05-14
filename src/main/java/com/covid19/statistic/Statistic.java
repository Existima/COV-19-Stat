package com.covid19.statistic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistic {

    private Integer infected;
    private Integer deceased;
    private Integer recovered;
    private Integer dailyInfected;
    private Integer dailyTested;
    private Integer dailyPositiveTests;
    private Integer dailyDeceased;
    private Integer dailyDeceasedDueToCovid;
    private Integer dailyRecovered;
    private Integer dailyQuarantine;

    @JsonProperty("lastUpdatedAtApify")
    private LocalDateTime date;

    public Integer getInfected() {
        return infected;
    }

    public void setInfected(Integer infected) {
        this.infected = infected;
    }

    public Integer getDeceased() {
        return deceased;
    }

    public void setDeceased(Integer deceased) {
        this.deceased = deceased;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getDailyInfected() {
        return dailyInfected;
    }

    public void setDailyInfected(Integer dailyInfected) {
        this.dailyInfected = dailyInfected;
    }

    public Integer getDailyTested() {
        return dailyTested;
    }

    public void setDailyTested(Integer dailyTested) {
        this.dailyTested = dailyTested;
    }

    public Integer getDailyPositiveTests() {
        return dailyPositiveTests;
    }

    public void setDailyPositiveTests(Integer dailyPositiveTests) {
        this.dailyPositiveTests = dailyPositiveTests;
    }

    public Integer getDailyDeceased() {
        return dailyDeceased;
    }

    public void setDailyDeceased(Integer dailyDeceased) {
        this.dailyDeceased = dailyDeceased;
    }

    public Integer getDailyDeceasedDueToCovid() {
        return dailyDeceasedDueToCovid;
    }

    public void setDailyDeceasedDueToCovid(Integer dailyDeceasedDueToCovid) {
        this.dailyDeceasedDueToCovid = dailyDeceasedDueToCovid;
    }

    public Integer getDailyRecovered() {
        return dailyRecovered;
    }

    public void setDailyRecovered(Integer dailyRecovered) {
        this.dailyRecovered = dailyRecovered;
    }

    public Integer getDailyQuarantine() {
        return dailyQuarantine;
    }

    public void setDailyQuarantine(Integer dailyQuarantine) {
        this.dailyQuarantine = dailyQuarantine;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "infected=" + infected +
                ", deceased=" + deceased +
                ", recovered=" + recovered +
                ", dailyInfected=" + dailyInfected +
                ", dailyTested=" + dailyTested +
                ", dailyPositiveTests=" + dailyPositiveTests +
                ", dailyDeceased=" + dailyDeceased +
                ", dailyDeceasedDueToCovid=" + dailyDeceasedDueToCovid +
                ", dailyRecovered=" + dailyRecovered +
                ", dailyQuarantine=" + dailyQuarantine +
                ", date=" + date +
                '}';
    }
}
