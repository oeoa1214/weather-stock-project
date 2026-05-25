package org.example.service;

import org.example.api.StockApiClient;
import org.example.api.StockJsonParser;
import org.example.model.Stock;

import java.util.List;

public class Parsing {

    // API 원본 JSON 가져오기
    public String getStockJson(Stock stock) {

        return StockApiClient.getStockData(
                stock.getSymbol()
        );
    }

    // 최근 7일 종가 리스트 가져오기
    public List<Double> getClosePrices(String json) {

        return StockJsonParser.parseClosePrices(json);
    }

    // 현재가 가져오기
    public double getCurrentPrice(String json) {

        return StockJsonParser.parseCurrentPrice(json);
    }
}