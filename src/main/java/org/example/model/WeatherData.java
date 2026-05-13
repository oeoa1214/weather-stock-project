package org.example.model;

public final class WeatherData {

    private final double temperature;
    private final double precipitation;
    private final double windspeed;
    private final double pm10;
    private final int weathercode;

    public WeatherData(double temperature,
                       double precipitation,
                       double windspeed,
                       double pm10,
                       int weathercode) {

        if (precipitation < 0) {
            throw new IllegalArgumentException("강수량은 음수가 될 수 없습니다.");
        }

        if (windspeed < 0) {
            throw new IllegalArgumentException("풍속은 음수가 될 수 없습니다.");
        }

        if (pm10 < 0) {
            throw new IllegalArgumentException("미세먼지 값은 음수가 될 수 없습니다.");
        }

        this.temperature = temperature;
        this.precipitation = precipitation;
        this.windspeed = windspeed;
        this.pm10 = pm10;
        this.weathercode = weathercode;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public double getPm10() {
        return pm10;
    }

    public int getWeathercode() {
        return weathercode;
    }
}