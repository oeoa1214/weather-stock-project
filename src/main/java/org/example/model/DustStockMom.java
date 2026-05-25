package org.example.model;

import java.util.List;

public class DustStockMom extends StockMom {

    public DustStockMom() {
        super("공기청정/위생");
    }

    @Override
    public List<Stock> getStocks() {
        return List.of(
                new Stock("위닉스", "044340.KS", "공기청정/위생"),
                new Stock("코웨이", "021240.KS", "공기청정/위생"),
                new Stock("LG전자", "066570.KS", "공기청정/위생"),
                new Stock("케이엠", "083550.KQ", "공기청정/위생"),
                new Stock("깨끗한나라", "004540.KS", "공기청정/위생")
        );
    }
}