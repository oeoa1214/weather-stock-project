package org.example.controller;

import org.example.model.CloseInfo;
import org.example.model.Stock;
import org.example.service.Hub3Data;
import org.example.service.Parsing;
import org.example.service.StockOvermind;
import org.example.util.StockFileReader;

import java.util.ArrayList;
import java.util.List;

public final class StockController {

    private final StockOvermind stockOvermind =
            new StockOvermind();

    private final Parsing parsing =
            new Parsing();

    public StockControllerData prepareAllStocks() {

        List<Stock> stocks =
                StockFileReader.loadAllStocks();

        if (stocks.size() != 25) {
            throw new IllegalStateException(
                    "전체 종목 수가 25개가 아닙니다. 현재 개수: "
                            + stocks.size()
            );
        }

        return prepareStocks(
                stocks
        );
    }

    public StockControllerData prepareStocks(
            List<Stock> stocks
    ) {

        if (stocks == null || stocks.isEmpty()) {
            throw new IllegalArgumentException(
                    "주식 목록은 비어 있을 수 없습니다."
            );
        }

        List<Hub3Data.Item> hub3Items =
                new ArrayList<>();

        List<CloseInfo> closeInfos =
                new ArrayList<>();

        for (Stock stock : stocks) {

            try {

                String realtimeJson =
                        stockOvermind.getStockJson(
                                stock
                        );

                String closeJson =
                        parsing.getStockJson(
                                stock
                        );

                double currentPrice =
                        stockOvermind.getCurrentPrice(
                                realtimeJson
                        );

                List<Double> closePrices =
                        stockOvermind.getClosePrices(
                                closeJson
                        );

                double returnRate =
                        calculateRealtimeReturnRate(
                                currentPrice,
                                closePrices
                        );

                hub3Items.add(
                        new Hub3Data.Item(
                                stock.getName(),
                                stock.getSymbol(),
                                stock.getTheme(),
                                currentPrice,
                                returnRate
                        )
                );

                closeInfos.add(
                        new CloseInfo(
                                stock,
                                closePrices
                        )
                );

                Thread.sleep(
                        300
                );

            } catch (Exception e) {

                System.out.println(
                        stock.getName()
                                + " 주식 정보 생성 실패"
                );
            }
        }

        if (hub3Items.isEmpty()) {
            throw new IllegalStateException(
                    "Hub3Data.Item 생성 결과가 비어 있습니다."
            );
        }

        if (closeInfos.isEmpty()) {
            throw new IllegalStateException(
                    "CloseInfo 생성 결과가 비어 있습니다."
            );
        }

        return new StockControllerData(
                hub3Items,
                closeInfos
        );
    }

    private double calculateRealtimeReturnRate(
            double currentPrice,
            List<Double> closePrices
    ) {

        if (currentPrice <= 0) {
            return 0.0;
        }

        if (closePrices == null || closePrices.size() < 2) {
            return 0.0;
        }

        double previousClose =
                closePrices.get(
                        closePrices.size() - 2
                );

        if (previousClose <= 0) {
            return 0.0;
        }

        return ((currentPrice - previousClose)
                / previousClose) * 100.0;
    }
}