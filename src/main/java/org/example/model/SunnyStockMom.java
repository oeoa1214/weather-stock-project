package org.example.model;

import java.util.List;

public class SunnyStockMom extends StockMom {

    public SunnyStockMom() {
        super("여행/소비");
    }

    @Override
    public List<Stock> getStocks() {
        return List.of(
                new Stock("대한항공", "003490.KS", "여행/소비"),
                new Stock("하나투어", "039130.KS", "여행/소비"),
                new Stock("호텔신라", "008770.KS", "여행/소비"),
                new Stock("CJ CGV", "079160.KS", "여행/소비"),
                new Stock("신세계", "004170.KS", "여행/소비")
        );
    }
}