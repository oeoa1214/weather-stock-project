package org.example.model;

public record Class7(
        String themeName,
        String stockName,
        double currentPrice,
        double returnRate
) {
    public Class7 {
        if (themeName == null || themeName.isBlank()) {
            throw new IllegalArgumentException(
                    "테마명은 비어 있을 수 없습니다."
            );
        }

        if (stockName == null || stockName.isBlank()) {
            throw new IllegalArgumentException(
                    "종목명은 비어 있을 수 없습니다."
            );
        }

        if (currentPrice <= 0) {
            throw new IllegalArgumentException(
                    "현재가는 0보다 커야 합니다."
            );
        }
    }
}