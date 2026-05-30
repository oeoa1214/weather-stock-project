package org.example.service;

import org.example.model.Class6;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Class7AppliedRateCalculator {

    public Map<String, Double> createAppliedRateMapByCount(
            Class6 class6Result,
            Map<String, Integer> countMap
    ) {
        Map<String, Double> appliedRateMap =
                new LinkedHashMap<>();

        if (class6Result == null) {
            return appliedRateMap;
        }

        Map<String, Double> averageRateMap =
                class6Result.getCumulativeReturnRates();

        if (averageRateMap == null || averageRateMap.isEmpty()) {
            return appliedRateMap;
        }

        for (String themeName : averageRateMap.keySet()) {

            double averageRate =
                    averageRateMap.getOrDefault(
                            themeName,
                            0.0
                    );

            int count =
                    0;

            if (countMap != null) {
                count =
                        countMap.getOrDefault(
                                themeName,
                                0
                        );
            }

            appliedRateMap.put(
                    themeName,
                    averageRate * count
            );
        }

        return appliedRateMap;
    }
}