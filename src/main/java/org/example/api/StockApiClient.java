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

    // 5번 / 6번 CloseInfo용: 최근 7일 일봉 JSON
    public static String getStockData(String symbol) {

        final String apiUrl =
                "https://query1.finance.yahoo.com/v8/finance/chart/"
                        + symbol
                        + "?range=7d&interval=1d";

        return request(apiUrl);
    }

    // 2번 / 3번 / 7번 Realtime용: 현재가 확인용 1일 분봉 JSON
    public static String getRealtimeStockData(String symbol) {

        final String apiUrl =
                "https://query1.finance.yahoo.com/v8/finance/chart/"
                        + symbol
                        + "?range=1d&interval=1m";

        return request(apiUrl);
    }

    private static String request(String apiUrl) {

        StringBuilder result =
                new StringBuilder();

        try {

            URL url =
                    new URL(apiUrl);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(
                    "GET"
            );

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