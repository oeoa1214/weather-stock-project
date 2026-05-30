package org.example.service;

import org.example.model.Class6;
import org.example.model.ViewClass6;

import java.util.Map;

public class ViewRun6 {

    public ViewClass6 createViewClass6(
            Class6 class6
    ) {
        if (class6 == null) {
            throw new IllegalArgumentException(
                    "Class6 결과는 null일 수 없습니다."
            );
        }

        Map<String, Double> returnRates =
                class6.getCumulativeReturnRates();

        if (returnRates == null || returnRates.isEmpty()) {
            throw new IllegalArgumentException(
                    "Class6 수익률 결과가 비어 있습니다."
            );
        }

        String bestThemeName =
                findBestThemeName(
                        returnRates
                );

        String weatherName =
                convertThemeToWeatherName(
                        bestThemeName
                );

        return new ViewClass6(
                weatherName,
                bestThemeName
        );
    }

    private String findBestThemeName(
            Map<String, Double> returnRates
    ) {
        String bestThemeName =
                "";

        double bestReturnRate =
                Double.NEGATIVE_INFINITY;

        for (Map.Entry<String, Double> entry : returnRates.entrySet()) {
            String themeName =
                    entry.getKey();

            double returnRate =
                    entry.getValue();

            if (returnRate > bestReturnRate) {
                bestReturnRate =
                        returnRate;

                bestThemeName =
                        themeName;
            }
        }

        if (bestThemeName.isBlank()) {
            throw new IllegalStateException(
                    "1등 테마를 찾을 수 없습니다."
            );
        }

        return bestThemeName;
    }

    private String convertThemeToWeatherName(
            String themeName
    ) {
        if (themeName.equals("편의점·간편식")) {
            return "비";
        }

        if (themeName.equals("여행·소비")) {
            return "맑음";
        }

        if (themeName.equals("공기청정·위생")) {
            return "미세먼지";
        }

        if (themeName.equals("냉방·여름소비")) {
            return "폭염";
        }

        if (themeName.equals("난방·겨울소비")) {
            return "한파";
        }

        throw new IllegalArgumentException(
                "날씨명으로 변환할 수 없는 테마입니다: " + themeName
        );
    }
}