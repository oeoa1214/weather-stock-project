package org.example.service;

import org.example.model.Stock;
import org.example.model.ThemeStrategy;
import org.example.util.StockFileReader;

import java.util.List;

public final class StockService {

    public List<Stock> getStocksByTheme(
            ThemeStrategy themeStrategy) {

        return StockFileReader.loadStocksByTheme(
                themeStrategy.getThemeName()
        );
    }
}