package org.example.service;

import org.example.api.WeatherApiClient;
import org.example.model.WeatherCondition;
import org.example.model.WeatherData;
import org.example.model.WeatherDecision;

public class WeatherDecisionService {

    private final WeatherService weatherService =
            new WeatherService();

    public WeatherDecision decideWeather() {

        String weatherJson =
                WeatherApiClient.getWeatherData();

        String airJson =
                WeatherApiClient.getAirQualityData();

        WeatherData weatherData =
                WeatherApiClient.parseWeatherData(
                        weatherJson,
                        airJson
                );

        WeatherCondition condition =
                weatherService.createConditionByData(
                        weatherData
                );

        return new WeatherDecision(
                weatherData,
                condition
        );
    }
}