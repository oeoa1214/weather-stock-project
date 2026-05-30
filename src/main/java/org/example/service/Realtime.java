package org.example.service;

import org.example.model.Stock;

import java.util.List;

public final class Realtime {

    private final Parsing priceService =
            new Parsing();

    // Realtime은 현재가 전담이므로 1일 분봉 JSON을 가져온다.
    public String getStockJson(
            Stock stock
    ) {

        return priceService.getRealtimeStockJson(
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

    // 5번 / 6번용: 7일 종가 리스트 기준 수익률
    public double getReturnRate(
            List<Double> closePrices
    ) {

        if (closePrices == null || closePrices.size() < 2) {
            return 0.0;
        }

        double firstPrice =
                closePrices.get(
                        0
                );

        double lastPrice =
                closePrices.get(
                        closePrices.size() - 1
                );

        if (firstPrice <= 0) {
            return 0.0;
        }

        return ((lastPrice - firstPrice)
                / firstPrice) * 100;
    }
}