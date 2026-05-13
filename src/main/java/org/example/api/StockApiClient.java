package org.example.api;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockApiClient {

    public static String getStockData(String symbol) {

        String apiUrl =
                "https://query1.finance.yahoo.com/v8/finance/chart/"
                        + symbol
                        + "?range=7d&interval=1d";

        StringBuilder result = new StringBuilder();

        try {

            URL url = new URL(apiUrl);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                System.out.println("HTTP Error : " + responseCode);
                return "";
            }


            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream())
                    );

            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }
    public static List<Double> parseClosePrices(String json) {

        List<Double> prices = new ArrayList<>();

        try {

            String closeKey = "\"close\":[";

            int start = json.indexOf(closeKey);

            if (start == -1) {
                return prices;
            }

            start += closeKey.length();
            int end =
                    json.indexOf("]", start);

            String closeData =
                    json.substring(start, end);

            String[] values = closeData.split(",");

            for (String value : values) {

                if (!value.equals("null")) {
                    prices.add(Double.parseDouble(value));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prices;
    }
    public static double parseCurrentPrice(String json) {

        try {

            String key = "\"regularMarketPrice\":";

            int start = json.indexOf(key);

            if (start == -1) {
                return -1;
            }

            start += key.length();

            int end = json.indexOf(",", start);

            String value =
                    json.substring(start, end);

            return Double.parseDouble(value);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}

