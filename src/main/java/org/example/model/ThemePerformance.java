package org.example.model;

public record ThemePerformance(
        String themeName,
        double averageReturn,
        String bestStock,
        double bestStockReturn
) {

    public ThemePerformance {
        if (themeName == null || themeName.isBlank()) {
            throw new IllegalArgumentException("테마명은 비어 있을 수 없습니다.");
        }

        if (bestStock == null || bestStock.isBlank()) {
            throw new IllegalArgumentException("최고 종목명은 비어 있을 수 없습니다.");
        }
    }
}