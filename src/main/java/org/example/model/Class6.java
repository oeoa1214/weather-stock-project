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

    /*
     * 평균 분석용 경로
     *
     * 기온 구간 분석처럼 key가 기존 테마명이 아닐 때 사용한다.
     * 예:
     * 5도↓, 5~15도, 15~22도, 22~28도, 28도↑
     *
     * 기존 setRate()는 테마명 검증을 하므로,

     */
    public void setAverageRates(
            Map<String, Double> averageRates
    ) {
        if (averageRates == null || averageRates.isEmpty()) {
            throw new IllegalArgumentException(
                    "averageRates는 비어 있을 수 없습니다."
            );
        }

        cumulativeReturnRates.clear();

        for (Map.Entry<String, Double> entry : averageRates.entrySet()) {
            String name =
                    entry.getKey();

            Double rate =
                    entry.getValue();

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException(
                        "평균 수익률 이름은 비어 있을 수 없습니다."
                );
            }

            if (rate == null) {
                throw new IllegalArgumentException(
                        "평균 수익률 값은 null일 수 없습니다: " + name
                );
            }

            cumulativeReturnRates.put(
                    name,
                    rate
            );
        }
    }

    public Map<String, Double> getCumulativeReturnRates() {
        return cumulativeReturnRates;
    }

    @Override
    public String toString() {
        return cumulativeReturnRates.toString();
    }
}