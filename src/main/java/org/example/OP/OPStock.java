package org.example.OP;

public record OPStock(
        String stockName,
        double currentPrice,
        double changeRate
) {
    public OPStock {
        if (stockName == null || stockName.isBlank()) {
            throw new IllegalArgumentException(
                    "종목 이름은 비어 있을 수 없습니다."
            );
        }

        if (currentPrice <= 0) {
            throw new IllegalArgumentException(
                    "현재가는 0보다 커야 합니다."
            );
        }
    }
}