package org.example.model;

public record Class1(
        double temperature,
        double precipitation,
        double windspeed,
        double pm10,
        int humidity,
        int weathercode,
        weatherMom condition
) {

    public Class1 {

        if (precipitation < 0) {
            throw new IllegalArgumentException(
                    "강수량은 음수가 될 수 없습니다."
            );
        }

        if (windspeed < 0) {
            throw new IllegalArgumentException(
                    "풍속은 음수가 될 수 없습니다."
            );
        }

        if (pm10 < 0) {
            throw new IllegalArgumentException(
                    "미세먼지 값은 음수가 될 수 없습니다."
            );
        }

        if (humidity < 0 || humidity > 100) {
            throw new IllegalArgumentException(
                    "습도는 0 이상 100 이하만 가능합니다."
            );
        }

        if (condition == null) {
            throw new IllegalArgumentException(
                    "날씨 조건은 null일 수 없습니다."
            );
        }
    }
}