package org.example.service;

import org.example.util.RandomRateMaker;

import java.util.LinkedHashMap;
import java.util.Map;

public class DailyThemeReturnGenerator {

    public Map<String, Double> createTodayReturns(
            String hitTheme,
            double bonusMaxRate
    ) {
        if (hitTheme == null || hitTheme.isBlank()) {
            throw new IllegalArgumentException(
                    "hitTheme은 비어 있을 수 없습니다."
            );
        }

        if (bonusMaxRate < 0.0 || bonusMaxRate > 3.0) {
            throw new IllegalArgumentException(
                    "날씨 보정치는 0~3 사이여야 합니다."
            );
        }

        Map<String, Double> todayReturns =
                new LinkedHashMap<>();

        todayReturns.put(
                "편의점·간편식",
                createOneThemeReturn(
                        hitTheme,
                        "편의점·간편식",
                        bonusMaxRate
                )
        );

        todayReturns.put(
                "여행·소비",
                createOneThemeReturn(
                        hitTheme,
                        "여행·소비",
                        bonusMaxRate
                )
        );

        todayReturns.put(
                "공기청정·위생",
                createOneThemeReturn(
                        hitTheme,
                        "공기청정·위생",
                        bonusMaxRate
                )
        );

        todayReturns.put(
                "냉방·여름소비",
                createOneThemeReturn(
                        hitTheme,
                        "냉방·여름소비",
                        bonusMaxRate
                )
        );

        todayReturns.put(
                "난방·겨울소비",
                createOneThemeReturn(
                        hitTheme,
                        "난방·겨울소비",
                        bonusMaxRate
                )
        );

        return todayReturns;
    }

    /*
     * 기존 테스트 코드가 createTodayReturns(String)로 되어 있으면
     * 깨지지 않도록 기본 보정치 2%로 둔다.
     */
    public Map<String, Double> createTodayReturns(
            String hitTheme
    ) {
        return createTodayReturns(
                hitTheme,
                2.0
        );
    }

    private double createOneThemeReturn(
            String hitTheme,
            String targetTheme,
            double bonusMaxRate
    ) {
        double baseReturn =
                RandomRateMaker.between(
                        -5.0,
                        5.0
                );

        double bonusReturn =
                0.0;

        if (hitTheme.equals(targetTheme)) {
            bonusReturn =
                    RandomRateMaker.between(
                            0.0,
                            bonusMaxRate
                    );
        }

        return baseReturn + bonusReturn;
    }
}