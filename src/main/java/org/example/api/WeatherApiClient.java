package org.example.api;

import org.example.model.WeatherData;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//→ 날씨 API 호출
//→ 미세먼지 API 호출
//→ 날씨 JSON을 WeatherData로 변환


public class WeatherApiClient {

    // 일반 날씨 API
    public static String getWeatherData() {

        String apiUrl =
                "https://api.open-meteo.com/v1/forecast?" +
                        "latitude=36.1195&longitude=128.3446" +
                        "&current=temperature_2m,precipitation,wind_speed_10m,weather_code";

        return requestApi(apiUrl);
    }

    // 미세먼지 API
    public static String getAirQualityData() {

        String apiUrl =
                "https://air-quality-api.open-meteo.com/v1/air-quality?" +
                        "latitude=36.1195&longitude=128.3446" +
                        "&current=pm10";

        return requestApi(apiUrl);
    }

    // 공통 API 요청
    private static String requestApi(String apiUrl) {

        StringBuilder result = new StringBuilder();

        try {

            URL url = new URL(apiUrl);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

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

    // JSON → WeatherData 변환
    public static WeatherData parseWeatherData(
            String weatherJson,
            String airJson
    ) {

        double temperature = 0;
        double precipitation = 0;
        double windspeed = 0;
        double pm10 = 0;
        int weathercode = 0;

        try {

            String currentWeatherJson =
                    weatherJson.substring(
                            weatherJson.indexOf("\"current\":")
                    );

            temperature =
                    extractDouble(
                            currentWeatherJson,
                            "\"temperature_2m\":"
                    );

            precipitation =
                    extractDouble(
                            currentWeatherJson,
                            "\"precipitation\":"
                    );

            windspeed =
                    extractDouble(
                            currentWeatherJson,
                            "\"wind_speed_10m\":"
                    );

            weathercode =
                    extractInt(
                            currentWeatherJson,
                            "\"weather_code\":"
                    );

            String currentAirJson =
                    airJson.substring(
                            airJson.indexOf("\"current\":")
                    );

            pm10 =
                    extractDouble(
                            currentAirJson,
                            "\"pm10\":"
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new WeatherData(
                temperature,
                precipitation,
                windspeed,
                pm10,
                weathercode
        );
    }

    private static double extractDouble(String json, String key) {

        int start = json.indexOf(key);

        if (start == -1) {
            return 0;
        }

        start += key.length();

        int endComma = json.indexOf(",", start);
        int endBrace = json.indexOf("}", start);

        int end;

        if (endComma == -1) {
            end = endBrace;
        } else if (endBrace == -1) {
            end = endComma;
        } else {
            end = Math.min(endComma, endBrace);
        }

        String value = json.substring(start, end).trim();

        return Double.parseDouble(value);
    }

    private static int extractInt(String json, String key) {

        int start = json.indexOf(key);

        if (start == -1) {
            return 0;
        }

        start += key.length();

        int endComma = json.indexOf(",", start);
        int endBrace = json.indexOf("}", start);

        int end;

        if (endComma == -1) {
            end = endBrace;
        } else if (endBrace == -1) {
            end = endComma;
        } else {
            end = Math.min(endComma, endBrace);
        }

        String value = json.substring(start, end).trim();

        return Integer.parseInt(value);
    }
}