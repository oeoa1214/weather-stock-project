package org.example.service;

import org.example.model.RecommendedStockInfo;
import org.example.model.StockSnapshot;
import org.example.model.ThemeStrategy;

import java.util.List;

public class RecommendedStockService {

    private final StockReasonService reasonService =
            new StockReasonService();

    public RecommendedStockInfo createRecommendedStockInfo(
            ThemeStrategy themeStrategy,
            List<StockSnapshot> snapshots
    ) {

        StockSnapshot bestSnapshot =
                snapshots.get(0);

        for (StockSnapshot snapshot : snapshots) {

            if (snapshot.getReturnRate()
                    > bestSnapshot.getReturnRate()) {

                bestSnapshot = snapshot;
            }
        }

        String reason =
                reasonService.getReason(
                        bestSnapshot.getStock().getName()
                );

        return new RecommendedStockInfo(
                themeStrategy.getThemeName(),
                bestSnapshot.getStock().getName(),
                bestSnapshot.getCurrentPrice(),
                bestSnapshot.getReturnRate(),
                reason
        );
    }
}