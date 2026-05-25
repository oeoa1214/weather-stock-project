package org.example.model;

import java.util.List;

public interface StockMomProvider {

    String themeName();

    List<Stock> getStocks();
}