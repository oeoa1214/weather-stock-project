package org.example.service;

import org.example.model.ThemeStrategy;
import org.example.model.WeatherCondition;

public class WeatherPrinterService {

    public void printSelectedWeather(
            WeatherCondition selected,
            ThemeStrategy themeStrategy,
            String randomFood
    ) {

        System.out.println();
        System.out.println("=== 최종 선택 날씨 ===");

        System.out.println(
                "날씨: "
                        + selected.getName()
        );

        System.out.println(
                "추천 테마: "
                        + themeStrategy.getThemeName()
        );

        System.out.println(
                "추천 음식: "
                        + randomFood
        );
    }
}