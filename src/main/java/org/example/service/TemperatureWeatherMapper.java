package org.example.service;

public final class TemperatureWeatherMapper {

    private TemperatureWeatherMapper() {
    }

    public static String toWeatherNameByTemperature(
            double temperature
    ) {
        if (temperature <= 5.0) {
            return "한파";
        }

        if (temperature >= 28.0) {
            return "폭염";
        }

        return "맑음";
    }

    public static String toWeatherNameByRange(
            String temperatureRange
    ) {
        if (temperatureRange == null || temperatureRange.isBlank()) {
            return "맑음";
        }

        if (temperatureRange.equals("5도↓")) {
            return "한파";
        }

        if (temperatureRange.equals("28도↑")) {
            return "폭염";
        }

        return "맑음";
    }
}