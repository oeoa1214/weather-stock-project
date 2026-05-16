package org.example.model;

public final class StockSnapshot {

    private final Stock stock;

    private final double currentPrice;

    private final double returnRate;

    public StockSnapshot(
            Stock stock,
            double currentPrice,
            double returnRate
    ) {

        if (stock == null) {
            throw new IllegalArgumentException(
                    "종목 정보는 null일 수 없습니다."
            );
        }

        if (currentPrice < 0) {
            throw new IllegalArgumentException(
                    "현재가는 음수가 될 수 없습니다."
            );
        }

        this.stock = stock;
        this.currentPrice = currentPrice;
        this.returnRate = returnRate;
    }

    public Stock getStock() {
        return stock;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getReturnRate() {
        return returnRate;
    }
}