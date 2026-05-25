package org.example.model;

import java.util.List;

public class RainStockMom extends StockMom {

    public RainStockMom() {
        super("배달/온라인");
    }

    @Override
    public List<Stock> getStocks() {
        return List.of(
                new Stock("BGF리테일", "282330.KS", "배달/온라인"),
                new Stock("GS리테일", "007070.KS", "배달/온라인"),
                new Stock("CJ제일제당", "097950.KS", "배달/온라인"),
                new Stock("농심", "004370.KS", "배달/온라인"),
                new Stock("오뚜기", "007310.KS", "배달/온라인")
        );
    }
}