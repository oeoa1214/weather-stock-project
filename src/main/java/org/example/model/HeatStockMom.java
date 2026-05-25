package org.example.model;

import java.util.List;

public class HeatStockMom extends StockMom {

    public HeatStockMom() {
        super("냉방/전력");
    }

    @Override
    public List<Stock> getStocks() {
        return List.of(
                new Stock("LG전자", "066570.KS", "냉방/전력"),
                new Stock("삼성전자", "005930.KS", "냉방/전력"),
                new Stock("한국전력", "015760.KS", "냉방/전력"),
                new Stock("LS ELECTRIC", "010120.KS", "냉방/전력"),
                new Stock("대한전선", "001440.KS", "냉방/전력")
        );
    }
}