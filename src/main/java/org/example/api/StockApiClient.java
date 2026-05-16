package org.example.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



//Yahoo Finance 주식 API 호출
// 종목 심볼로 주가 JSON 받아오기

public final class StockApiClient {

    private StockApiClient() {
    }

    public static String getStockData(String symbol) {

        final String apiUrl =
                "https://query1.finance.yahoo.com/v8/finance/chart/"
                        + symbol
                        + "?range=7d&interval=1d";

        StringBuilder result = new StringBuilder();

        try {

            URL url = new URL(apiUrl);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0"
            );

            int responseCode =
                    conn.getResponseCode();

            if (responseCode != 200) {

                System.out.println(
                        "HTTP Error : "
                                + responseCode
                );

                return "";
            }

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()
                            )
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
}