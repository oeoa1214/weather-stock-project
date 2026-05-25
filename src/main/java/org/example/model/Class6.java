package org.example.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Class6 {

    private final Map<String, Double> cumulativeReturnRates =
            new LinkedHashMap<>();

    public Class6() {
        reset();
    }

    public void reset() {
        cumulativeReturnRates.clear();

        cumulativeReturnRates.put("편의점·간편식", 0.0);
        cumulativeReturnRates.put("여행·소비", 0.0);
        cumulativeReturnRates.put("공기청정·위생", 0.0);
        cumulativeReturnRates.put("냉방·여름소비", 0.0);
        cumulativeReturnRates.put("난방·겨울소비", 0.0);
    }

    public void setRate(
            String themeName,
            double cumulativeReturnRate
    ) {
        if (!cumulativeReturnRates.containsKey(themeName)) {
            throw new IllegalArgumentException(
                    "Class6에 없는 테마입니다: " + themeName
            );
        }

        cumulativeReturnRates.put(
                themeName,
                cumulativeReturnRate
        );
    }

    public Map<String, Double> getCumulativeReturnRates() {
        return cumulativeReturnRates;
    }

    @Override
    public String toString() {
        return cumulativeReturnRates.toString();
    }
}