package org.example.service;

import org.example.model.Stock;

import java.util.List;

public final class StockOvermind {

    //파싱한테 명령
    private final Parsing priceService =
            new Parsing();
//파싱한테 개별종목 시키기
    public String getStockJson(
            Stock stock
    ) {

        return priceService.getRealtimeStockJson(
                stock
        );
    }
//파싱한테 현재가 시키기
    public double getCurrentPrice(
            String json
    ) {

        return priceService.getCurrentPrice(
                json
        );
    }
  //파싱한테 종가 시키기
     public List<Double> getClosePrices(
            String json
    ) {

        return priceService.getClosePrices(
                json
        );
    }
}