package org.example.service;

public record KospiWeatherDay(
        String date,
        double averageTemperature,
        double precipitation,
        double pm10,
        double kospiReturn
) {
}