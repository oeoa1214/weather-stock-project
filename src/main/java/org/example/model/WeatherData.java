package org.example.model;
//레코드의 컴팩트 생성자
public record WeatherData(
        double temperature,
        double precipitation,
        double windspeed,
        double pm10,
        int weathercode
) {

    public WeatherData {
        if (precipitation < 0) {
            throw new IllegalArgumentException("강수량은 음수가 될 수 없습니다.");
        }

        if (windspeed < 0) {
            throw new IllegalArgumentException("풍속은 음수가 될 수 없습니다.");
        }

        if (pm10 < 0) {
            throw new IllegalArgumentException("미세먼지 값은 음수가 될 수 없습니다.");
        }
    }
}