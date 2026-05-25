package org.example.service;

import org.example.api.WeatherApiClient;
import org.example.model.WeatherRawData;

public class Stem1 {

    public WeatherRawData sendToStem2() {

        String weatherJson =
                WeatherApiClient.getWeatherData();

        String airJson =
                WeatherApiClient.getAirQualityData();

        return new WeatherRawData(
                weatherJson,
                airJson
        );
    }
}