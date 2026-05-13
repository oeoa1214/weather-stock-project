package org.example.service;

import org.example.api.StockApiClient;
import org.example.model.Stock;
import org.example.model.StockSnapshot;

import java.util.ArrayList;
import java.util.List;

public final class MarketDataService {

    public List<StockSnapshot> createSnapshots(
            List<Stock> stocks) {

        final List<StockSnapshot> snapshots =
                new ArrayList<>();

        for (Stock stock : stocks) {

            try {

                final String json =
                        StockApiClient.getStockData(
                                stock.getSymbol()
                        );

                final double currentPrice =
                        StockApiClient.parseCurrentPrice(
                                json
                        );

                snapshots.add(
                        new StockSnapshot(
                                stock,
                                currentPrice
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