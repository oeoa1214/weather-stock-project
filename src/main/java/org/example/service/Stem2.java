package org.example.service;

import org.example.model.WeatherRawData;
import org.example.model.weatherMom;

public class Stem2 {

    private double temperature;
    private double precipitation;
    private double windspeed;
    private double pm10;
    private int humidity;
    private int weathercode;

    private weatherMom judgedWeather;

    public void receiveFromStem1(
            WeatherRawData rawData
    ) {
        if (rawData == null) {
            throw new IllegalArgumentException(
                    "Stem1에서 받은 rawData는 null일 수 없습니다."
            );
        }

        parseRawData(
                rawData.weatherJson(),
                rawData.airJson()
        );
    }

    public double sendTemperatureToJudge2() {
        return temperature;
    }

    public double sendPrecipitationToJudge2() {
        return precipitation;
    }

    public double sendPm10ToJudge2() {
        return pm10;
    }

    public void receiveFromJudge2(
            weatherMom judgedWeather
    ) {
        if (judgedWeather == null) {
            throw new IllegalArgumentException(
                    "Judge2 결과는 null일 수 없습니다."
            );
        }

        this.judgedWeather =
                judgedWeather;
    }

    public double sendTemperatureToRun1() {
        return temperature;
    }

    public double sendPrecipitationToRun1() {
        return precipitation;
    }

    public double sendWindspeedToRun1() {
        return windspeed;
    }

    public double sendPm10ToRun1() {
        return pm10;
    }

    public int sendHumidityToRun1() {
        return humidity;
    }

    public int sendWeathercodeToRun1() {
        return weathercode;
    }

    public weatherMom sendConditionToRun1() {
        if (judgedWeather == null) {
            throw new IllegalStateException(
                    "Stem2에 Judge2 결과가 없습니다."
            );
        }

        return judgedWeather;
    }

    public weatherMom sendToStem3() {
        if (judgedWeather == null) {
            throw new IllegalStateException(
                    "Stem2에 Judge2 결과가 없습니다."
            );
        }

        return judgedWeather;
    }

    private void parseRawData(
            String weatherJson,
            String airJson
    ) {
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

            humidity =
                    extractInt(
                            currentWeatherJson,
                            "\"relative_humidity_2m\":"
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
            throw new IllegalStateException(
                    "Stem2에서 raw 날씨 데이터를 숫자값으로 정리하는 중 실패했습니다.",
                    e
            );
        }
    }

    private double extractDouble(
            String json,
            String key
    ) {
        int start =
                json.indexOf(key);

        if (start == -1) {
            return 0;
        }

        start += key.length();

        int end =
                findValueEndIndex(
                        json,
                        start
                );

        String value =
                json.substring(
                        start,
                        end
                ).trim();

        return Double.parseDouble(value);
    }

    private int extractInt(
            String json,
            String key
    ) {
        int start =
                json.indexOf(key);

        if (start == -1) {
            return 0;
        }

        start += key.length();

        int end =
                findValueEndIndex(
                        json,
                        start
                );

        String value =
                json.substring(
                        start,
                        end
                ).trim();

        return Integer.parseInt(value);
    }

    private int findValueEndIndex(
            String json,
            int start
    ) {
        int endComma =
                json.indexOf(",", start);

        int endBrace =
                json.indexOf("}", start);

        if (endComma == -1 && endBrace == -1) {
            throw new IllegalStateException(
                    "JSON 값의 끝을 찾지 못했습니다."
            );
        }

        if (endComma == -1) {
            return endBrace;
        }

        if (endBrace == -1) {
            return endComma;
        }

        return Math.min(
                endComma,
                endBrace
        );
    }
}