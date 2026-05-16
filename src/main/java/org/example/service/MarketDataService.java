package org.example.service;

import org.example.model.Stock;
import org.example.model.StockSnapshot;

import java.util.ArrayList;
import java.util.List;

public final class MarketDataService {

    private final StockPriceService priceService =
            new StockPriceService();

    private final ReturnRateService returnRateService =
            new ReturnRateService();

    public List<StockSnapshot> createSnapshots(
            List<Stock> stocks) {

        final List<StockSnapshot> snapshots =
                new ArrayList<>();

        for (Stock stock : stocks) {

            try {

                String json =
                        priceService.getStockJson(stock);

                final double currentPrice =
                        priceService.getCurrentPrice(json);

                List<Double> prices =
                        priceService.getClosePrices(json);

                double returnRate =
                        returnRateService.calculateReturnRate(
                                prices
                        );

                snapshots.add(
                        new StockSnapshot(
                                stock,
                                currentPrice,
                                returnRate
                        )
                );

                Thread.sleep(300);

            } catch (Exception e) {

                System.out.println(
                        stock.getName()
                                + " 시세 조회 실패"
                );
            }
        }

        return snapshots;
    }
}