package org.example.model;

import java.util.List;

public abstract class ThemeStrategy {

    protected String themeName;

    public ThemeStrategy(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }

    public abstract List<Stock> getRecommendedStocks();
}