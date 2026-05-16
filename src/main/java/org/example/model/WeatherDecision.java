package org.example.model;

public record WeatherDecision(
        WeatherData weatherData,
        WeatherCondition condition
) {

    public WeatherDecision {

        if (weatherData == null) {
            throw new IllegalArgumentException(
                    "날씨 데이터는 null일 수 없습니다."
            );
        }

        if (condition == null) {
            throw new IllegalArgumentException(
                    "날씨 조건은 null일 수 없습니다."
            );
        }
    }
}