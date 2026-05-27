package org.example.controller;

import org.example.model.themeMom;
import org.example.model.weatherMom;
import org.example.service.Judge2;
import org.example.service.KospiWeatherCsvReader;
import org.example.service.KospiWeatherDay;
import org.example.service.WeatherThemeConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KospiWeatherAverageController {

    private final KospiWeatherCsvReader reader =
            new KospiWeatherCsvReader();

    private final Judge2 judge2 =
            new Judge2();

    private final WeatherThemeConverter converter =
            new WeatherThemeConverter();

    private final Map<String, Double> sumMap =
            new LinkedHashMap<>();

    private final Map<String, Integer> countMap =
            new LinkedHashMap<>();

    private List<KospiWeatherDay> days;

    private int index =
            0;

    public void start(
            String filePath
    ) {

        days =
                reader.read(
                        filePath
                );

        index =
                0;

        resetMaps();
    }

    private void resetMaps() {

        sumMap.clear();
        countMap.clear();

        sumMap.put("편의점·간편식", 0.0);
        sumMap.put("여행·소비", 0.0);
        sumMap.put("공기청정·위생", 0.0);
        sumMap.put("냉방·여름소비", 0.0);
        sumMap.put("난방·겨울소비", 0.0);

        countMap.put("편의점·간편식", 0);
        countMap.put("여행·소비", 0);
        countMap.put("공기청정·위생", 0);
        countMap.put("냉방·여름소비", 0);
        countMap.put("난방·겨울소비", 0);
    }

    public boolean isFinished() {
        return index >= days.size();
    }

    public int getCurrentDay() {
        return index;
    }

    public int getTotalDay() {
        return days.size();
    }

    public Map<String, Double> nextDay() {

        if (index >= days.size()) {
            return createAverageMap();
        }

        KospiWeatherDay day =
                days.get(index);

        weatherMom judgedWeather =
                judge2.judge(
                        day.averageTemperature(),
                        day.precipitation(),
                        day.pm10()
                );

        themeMom theme =
                converter.convert(
                        judgedWeather
                );

        String themeName =
                theme.getThemeName();

        double currentSum =
                sumMap.get(themeName);

        int currentCount =
                countMap.get(themeName);

        sumMap.put(
                themeName,
                currentSum + day.kospiReturn()
        );

        countMap.put(
                themeName,
                currentCount + 1
        );

        index++;

        return createAverageMap();
    }

    private Map<String, Double> createAverageMap() {

        Map<String, Double> averageMap =
                new LinkedHashMap<>();

        for (String themeName : sumMap.keySet()) {

            double sum =
                    sumMap.get(themeName);

            int count =
                    countMap.get(themeName);

            double average =
                    0.0;

            if (count > 0) {
                average =
                        sum / count;
            }

            averageMap.put(
                    themeName,
                    average
            );
        }

        return averageMap;
    }
}