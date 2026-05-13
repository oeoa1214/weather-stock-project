package org.example.model;

public final class ThemePerformance {

    private final String themeName;
    private final double averageReturn;

    private final String bestStock;
    private final double bestStockReturn;

    public ThemePerformance(String themeName,
                            double averageReturn,
                            String bestStock,
                            double bestStockReturn) {

        this.themeName = themeName;
        this.averageReturn = averageReturn;
        this.bestStock = bestStock;
        this.bestStockReturn = bestStockReturn;
    }

    public String getThemeName() {
        return themeName;
    }

    public double getAverageReturn() {
        return averageReturn;
    }

    public String getBestStock() {
        return bestStock;
    }

    public double getBestStockReturn() {
        return bestStockReturn;
    }
}