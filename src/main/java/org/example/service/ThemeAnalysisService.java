package org.example.service;

import org.example.model.Stock;
import org.example.model.ThemePerformance;
import org.example.model.ThemeStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThemeAnalysisService {

    public ThemePerformance analyzeTheme(
            ThemeStrategy themeStrategy,
            Map<String, Double> returns) {

        double sum = 0;

        double max = -999;

        String bestStock = "";

        for (String name : returns.keySet()) {

            double value = returns.get(name);

            sum += value;

            if (value > max) {
                max = value;
                bestStock = name;
            }
        }

        double average =
                sum / returns.size();

        return new ThemePerformance(
                themeStrategy.getThemeName(),
                average,
                bestStock,
                max
        );
    }

    // 최고 성과 테마 찾기
    public ThemePerformance findBestTheme(
            List<ThemePerformance> performances) {

        ThemePerformance best =
                performances.get(0);

        for (ThemePerformance p : performances) {

            if (p.getAverageReturn()
                    > best.getAverageReturn()) {

                best = p;
            }
        }

        return best;
    }
}