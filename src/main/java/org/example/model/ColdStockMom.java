package org.example.model;

import java.util.List;

public class ColdStockMom extends StockMom {

    public ColdStockMom() {
        super("난방/에너지");
    }

    @Override
    public List<Stock> getStocks() {
        return List.of(
                new Stock("경동나비엔", "009450.KS", "난방/에너지"),
                new Stock("한국가스공사", "036460.KS", "난방/에너지"),
                new Stock("서울가스", "017390.KS", "난방/에너지"),
                new Stock("대성에너지", "117580.KS", "난방/에너지"),
                new Stock("지역난방공사", "071320.KS", "난방/에너지")
        );
    }
}