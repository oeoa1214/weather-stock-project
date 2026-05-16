package org.example.service;

import org.example.model.Stock;

import java.util.List;

public class SelectedStockService {

    private final StockPriceService priceService =
            new StockPriceService();

    public List<Double> getPriceTrend(Stock stock) {

        String json =
                priceService.getStockJson(stock);

        return priceService.getClosePrices(json);
    }
}