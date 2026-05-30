package org.example.service;

import org.example.model.Class6;

import java.util.LinkedHashMap;
import java.util.Map;

public class Class7AccumulatedRate {

    private final Map<String, Double> accumulatedRateMap =
            new LinkedHashMap<>();

    public Class7AccumulatedRate() {
        reset();
    }

    public void reset() {
        accumulatedRateMap.clear();

        accumulatedRateMap.put("편의점·간편식", 0.0);
        accumulatedRateMap.put("여행·소비", 0.0);
        accumulatedRateMap.put("공기청정·위생", 0.0);
        accumulatedRateMap.put("냉방·여름소비", 0.0);
        accumulatedRateMap.put("난방·겨울소비", 0.0);
    }

    public Class6 addAndCreateClass6(
            Class6 class6Result
    ) {
        if (class6Result == null) {
            throw new IllegalArgumentException(
                    "Class6 결과는 null일 수 없습니다."
            );
        }

        Map<String, Double> rateMap =
                class6Result.getCumulativeReturnRates();

        if (rateMap == null || rateMap.isEmpty()) {
            return createClass6FromAccumulatedMap();
        }

        for (Map.Entry<String, Double> entry : rateMap.entrySet()) {
            String themeName =
                    entry.getKey();

            Double rate =
                    entry.getValue();

            if (rate == null) {
                continue;
            }

            if (!accumulatedRateMap.containsKey(themeName)) {
                continue;
            }

            double currentRate =
                    accumulatedRateMap.get(
                            themeName
                    );

            accumulatedRateMap.put(
                    themeName,
                    currentRate + rate
            );
        }

        return createClass6FromAccumulatedMap();
    }

    private Class6 createClass6FromAccumulatedMap() {
        Class6 class6 =
                new Class6();

        class6.setAverageRates(
                new LinkedHashMap<>(
                        accumulatedRateMap
                )
        );

        return class6;
    }
}