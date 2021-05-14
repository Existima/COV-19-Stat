package com.covid19.statistic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class SaveClass {
    //JsonReader reader = new JsonReader();
    //Statistic[] statistic = reader.readJsonFromUrl("https://api.apify.com/v2/datasets/L3VCmhMeX0KUQeJto/items?format=json&clean=1&fbclid=IwAR1ZEcY32PT-6ctGcL4XfVF1D2Sfe3G6WAqwvmdwP2a8hZ9aw3dVbbiBl8E");



//    public class JsonReader {
//
//        private static String readAll(Reader rd) throws IOException {
//            StringBuilder sb = new StringBuilder();
//            int cp;
//            while ((cp = rd.read()) != -1) {
//                sb.append((char) cp);
//            }
//            return sb.toString();
//        }
//
//        public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//            InputStream is = new URL(url).openStream();
//            try {
//                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//                String jsonText = readAll(rd);
//                JSONObject json = new JSONObject(jsonText);
//                return json;
//            } finally {
//                is.close();
//            }
//        }
//    }
}
