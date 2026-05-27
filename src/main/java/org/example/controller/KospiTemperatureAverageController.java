package org.example.controller;

import org.example.service.KospiWeatherCsvReader;
import org.example.service.KospiWeatherDay;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KospiTemperatureAverageController {

    private final KospiWeatherCsvReader reader =
            new KospiWeatherCsvReader();

    private final Map<String, Double> sumMap =
            new LinkedHashMap<>();

    private final Map<String, Integer> countMap =
            new LinkedHashMap<>();

    private List<KospiWeatherDay> days =
            List.of();

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

        sumMap.put("5도↓", 0.0);
        sumMap.put("5~15도", 0.0);
        sumMap.put("15~22도", 0.0);
        sumMap.put("22~28도", 0.0);
        sumMap.put("28도↑", 0.0);

        countMap.put("5도↓", 0);
        countMap.put("5~15도", 0);
        countMap.put("15~22도", 0);
        countMap.put("22~28도", 0);
        countMap.put("28도↑", 0);
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
        if (days.isEmpty()) {
            throw new IllegalStateException(
                    "KOSPI 기온 분석 모드가 시작되지 않았습니다. start()를 먼저 호출해야 합니다."
            );
        }

        if (isFinished()) {
            return createAverageMap();
        }

        KospiWeatherDay day =
                days.get(
                        index
                );

        String temperatureRange =
                getTemperatureRange(
                        day.averageTemperature()
                );

        double currentSum =
                sumMap.get(
                        temperatureRange
                );

        int currentCount =
                countMap.get(
                        temperatureRange
                );

        sumMap.put(
                temperatureRange,
                currentSum + day.kospiReturn()
        );

        countMap.put(
                temperatureRange,
                currentCount + 1
        );

        index++;

        return createAverageMap();
    }

    private String getTemperatureRange(
            double temperature
    ) {
        if (temperature <= 5.0) {
            return "5도↓";
        }

        if (temperature <= 15.0) {
            return "5~15도";
        }

        if (temperature <= 22.0) {
            return "15~22도";
        }

        if (temperature <= 28.0) {
            return "22~28도";
        }

        return "28도↑";
    }

    private Map<String, Double> createAverageMap() {
        Map<String, Double> averageMap =
                new LinkedHashMap<>();

        for (String rangeName : sumMap.keySet()) {
            double sum =
                    sumMap.get(
                            rangeName
                    );

            int count =
                    countMap.get(
                            rangeName
                    );

            double average =
                    0.0;

            if (count > 0) {
                average =
                        sum / count;
            }

            averageMap.put(
                    rangeName,
                    average
            );
        }

        return averageMap;
    }
}