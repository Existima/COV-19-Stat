package com.covid19.statistic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URL;

public class JsonReader {
    private static ObjectMapper mapper = new ObjectMapper();

    public JsonReader() {
        mapper.registerModule(new JavaTimeModule());
    }
    public Statistic[] readData() {
        try {
            return mapper.readValue(new URL("https://api.apify.com/v2/datasets/L3VCmhMeX0KUQeJto/items?format=json&clean=1&fbclid=IwAR1ZEcY32PT-6ctGcL4XfVF1D2Sfe3G6WAqwvmdwP2a8hZ9aw3dVbbiBl8E"), Statistic[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Statistic[0];
        }
    }

}
