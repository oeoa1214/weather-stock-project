package org.example.service;

import org.example.api.StockApiClient;
import org.example.model.Stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockAnalysisService {

    // 종목별 최근 7일 수익률 계산
    public Map<String, Double> calculateReturns(List<Stock> stocks) {

        Map<String, Double> result = new HashMap<>();

        for (Stock stock : stocks) {

            try {

                String json =
                        StockApiClient.getStockData(
                                stock.getSymbol()
                        );

                List<Double> prices =
                        StockApiClient.parseClosePrices(json);

                if (prices.size() >= 2) {

                    double firstPrice = prices.get(0);

                    double lastPrice =
                            prices.get(prices.size() - 1);

                    double returnRate =
                            ((lastPrice - firstPrice)
                                    / firstPrice) * 100;

                    result.put(
                            stock.getName(),
                            returnRate
                    );
                }

                // 요청 너무 빠르면 막히니까 잠깐 대기
                Thread.sleep(500);

            } catch (Exception e) {
                System.out.println(
                        stock.getName()
                                + " 분석 실패"
                );
            }
        }

        return result;
    }
}