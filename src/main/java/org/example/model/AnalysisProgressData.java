package org.example.model;

import java.util.LinkedHashMap;
import java.util.Map;

public record AnalysisProgressData(
        int currentDay,
        int totalDay,
        String currentDate,
        String currentKey,
        String colorWeatherName,
        double averageTemperature,
        double precipitation,
        double pm10,
        Map<String, Integer> countMap,
        Map<String, Integer> appliedCountMap
) {

    public AnalysisProgressData {
        if (currentDay < 0) {
            throw new IllegalArgumentException(
                    "현재 진행일은 음수가 될 수 없습니다."
            );
        }

        if (totalDay < 0) {
            throw new IllegalArgumentException(
                    "전체 진행일은 음수가 될 수 없습니다."
            );
        }

        if (currentDate == null) {
            currentDate =
                    "";
        }

        if (currentKey == null) {
            currentKey =
                    "";
        }

        if (colorWeatherName == null || colorWeatherName.isBlank()) {
            colorWeatherName =
                    "맑음";
        }

        if (countMap == null) {
            countMap =
                    Map.of();
        } else {
            countMap =
                    new LinkedHashMap<>(
                            countMap
                    );
        }

        if (appliedCountMap == null) {
            appliedCountMap =
                    Map.of();
        } else {
            appliedCountMap =
                    new LinkedHashMap<>(
                            appliedCountMap
                    );
        }
    }
}