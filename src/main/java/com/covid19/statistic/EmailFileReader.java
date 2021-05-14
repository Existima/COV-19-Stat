package com.covid19.statistic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmailFileReader {

    public List<String> getEmails(String fileName) {

        List<String> addressList = new ArrayList<>();

        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {

            String str;
            while ((str = br.readLine()) != null) {
                addressList.add(str);
            }

            return addressList;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
