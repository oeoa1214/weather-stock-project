package org.example.service;

import org.example.model.Stock;

import java.util.List;

public final class Realtime {

    private final Parsing priceService =
            new Parsing();

    public String getStockJson(
            Stock stock
    ) {

        return priceService.getStockJson(
                stock
        );
    }

    public double getCurrentPrice(
            String json
    ) {

        return priceService.getCurrentPrice(
                json
        );
    }

    public List<Double> getClosePrices(
            String json
    ) {

        return priceService.getClosePrices(
                json
        );
    }

    public double getReturnRate(
            List<Double> closePrices
    ) {

        double firstPrice =
                closePrices.get(0);

        double lastPrice =
                closePrices.get(
                        closePrices.size() - 1
                );

        return ((lastPrice - firstPrice)
                / firstPrice) * 100;
    }
}