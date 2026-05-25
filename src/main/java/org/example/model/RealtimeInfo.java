package org.example.model;

public record RealtimeInfo(
        Stock stock,
        double currentPrice,
        double returnRate
) {

    public RealtimeInfo {
        if (stock == null) {
            throw new IllegalArgumentException(
                    "Stock은 null일 수 없습니다."
            );
        }

        if (currentPrice < 0) {
            throw new IllegalArgumentException(
                    "현재가는 음수가 될 수 없습니다."
            );
        }
    }
}