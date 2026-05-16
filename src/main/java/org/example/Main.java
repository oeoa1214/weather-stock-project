package org.example;
import org.example.controller.AppController;
import org.example.api.WeatherApiClient;
import org.example.model.WeatherData;

public class Main {

    public static void main(String[] args) {

        String weatherJson =
                WeatherApiClient.getWeatherData();

        String airJson =
                WeatherApiClient.getAirQualityData();

        WeatherData weatherData =
                WeatherApiClient.parseWeatherData(
                        weatherJson,
                        airJson
                );

        System.out.println();
        System.out.println("=== 날씨 원본 데이터 확인 ===");
        System.out.println("온도: " + weatherData.temperature());
        System.out.println("강수량: " + weatherData.precipitation());
        System.out.println("풍속: " + weatherData.windspeed());
        System.out.println("미세먼지(pm10): " + weatherData.pm10());
        System.out.println("날씨 코드: " + weatherData.weathercode());

        AppController appController =
                new AppController();

        appController.run();
    }
}