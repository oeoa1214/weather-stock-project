package org.example.model;

public final class StockSnapshot {

    private final Stock stock;

    private final double currentPrice;

    public StockSnapshot(
            Stock stock,
            double currentPrice) {

        this.stock = stock;
        this.currentPrice = currentPrice;
    }

    public Stock getStock() {
        return stock;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
}