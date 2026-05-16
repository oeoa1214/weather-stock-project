package org.example.service;

import org.example.model.ThemePerformance;
import org.example.model.ThemeStrategy;

import java.util.Map;

public class ThemeAnalysisService {

    private final ThemeAverageService averageService =
            new ThemeAverageService();

    private final BestStockService bestStockService =
            new BestStockService();

    public ThemePerformance analyzeTheme(
            ThemeStrategy themeStrategy,
            Map<String, Double> returns
    ) {

        double average =
                averageService.calculateAverage(
                        returns
                );

        String bestStock =
                bestStockService.findBestStock(
                        returns
                );

        double bestReturn =
                bestStockService.findBestReturn(
                        returns
                );

        return new ThemePerformance(
                themeStrategy.getThemeName(),
                average,
                bestStock,
                bestReturn
        );
    }
}