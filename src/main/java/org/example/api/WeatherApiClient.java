package org.example.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class WeatherApiClient {

    private WeatherApiClient() {
    }

    public static String getWeatherData() {

        String apiUrl =
                "https://api.open-meteo.com/v1/forecast?"
                        + "latitude=36.1195&longitude=128.3446"
                        + "&current=temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m,weather_code";

        String result =
                requestApi(apiUrl);

        if (result.isBlank()) {
            return createFallbackWeatherJson();
        }

        return result;
    }

    public static String getAirQualityData() {

        String apiUrl =
                "https://air-quality-api.open-meteo.com/v1/air-quality?"
                        + "latitude=36.1195&longitude=128.3446"
                        + "&current=pm10";

        String result =
                requestApi(apiUrl);

        if (result.isBlank()) {
            return createFallbackAirJson();
        }

        return result;
    }

    private static String requestApi(
            String apiUrl
    ) {
        StringBuilder result =
                new StringBuilder();

        try {
            URL url =
                    new URL(apiUrl);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode =
                    conn.getResponseCode();

            if (responseCode < 200 || responseCode >= 300) {
                System.out.println(
                        "API 응답 실패: " + responseCode + " / " + apiUrl
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
            System.out.println(
                    "API 호출 실패. 기본 날씨값으로 대체합니다."
            );
        }

        return result.toString();
    }

    private static String createFallbackWeatherJson() {
        return "{"
                + "\"current\":{"
                + "\"temperature_2m\":20.0,"
                + "\"relative_humidity_2m\":50,"
                + "\"precipitation\":0.0,"
                + "\"wind_speed_10m\":1.0,"
                + "\"weather_code\":0"
                + "}"
                + "}";
    }

    private static String createFallbackAirJson() {
        return "{"
                + "\"current\":{"
                + "\"pm10\":30.0"
                + "}"
                + "}";
    }
}