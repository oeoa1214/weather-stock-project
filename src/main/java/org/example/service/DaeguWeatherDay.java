package org.example.service;

public record DaeguWeatherDay(
        double averageTemperature,
        double precipitation,
        double pm10
) {
}