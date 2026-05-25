package org.example.model;

public record WeatherRawData(
        String weatherJson,
        String airJson
) {

    public WeatherRawData {

        if (weatherJson == null || weatherJson.isBlank()) {
            throw new IllegalArgumentException(
                    "날씨 API raw 값은 비어 있을 수 없습니다."
            );
        }

        if (airJson == null || airJson.isBlank()) {
            throw new IllegalArgumentException(
                    "미세먼지 API raw 값은 비어 있을 수 없습니다."
            );
        }
    }
}