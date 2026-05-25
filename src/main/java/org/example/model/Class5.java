package org.example.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Class5 {

    private final Map<String, Double> sevenDayReturnRates =
            new LinkedHashMap<>();

    public Class5() {
        reset();
    }

    public void reset() {
        sevenDayReturnRates.clear();

        sevenDayReturnRates.put("편의점·간편식", 0.0);
        sevenDayReturnRates.put("여행·소비", 0.0);
        sevenDayReturnRates.put("공기청정·위생", 0.0);
        sevenDayReturnRates.put("냉방·여름소비", 0.0);
        sevenDayReturnRates.put("난방·겨울소비", 0.0);
    }

    public void setRate(
            String themeName,
            double sevenDayReturnRate
    ) {
        if (!sevenDayReturnRates.containsKey(themeName)) {
            throw new IllegalArgumentException(
                    "Class5에 없는 테마입니다: " + themeName
            );
        }

        sevenDayReturnRates.put(
                themeName,
                sevenDayReturnRate
        );
    }

    public Map<String, Double> getSevenDayReturnRates() {
        return sevenDayReturnRates;
    }

    @Override
    public String toString() {
        return sevenDayReturnRates.toString();
    }
}