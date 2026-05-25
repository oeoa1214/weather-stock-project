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

        return requestApi(apiUrl);
    }

    public static String getAirQualityData() {

        String apiUrl =
                "https://air-quality-api.open-meteo.com/v1/air-quality?"
                        + "latitude=36.1195&longitude=128.3446"
                        + "&current=pm10";

        return requestApi(apiUrl);
    }

    private static String requestApi(String apiUrl) {

        StringBuilder result =
                new StringBuilder();

        try {
            URL url =
                    new URL(apiUrl);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

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