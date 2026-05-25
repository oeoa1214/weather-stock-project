package org.example.model;

public record Class4(
        String themeName,
        String stockName,
        double currentPrice,
        double returnRate,
        String reason
) {

    public Class4 {

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

        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException(
                    "추천 이유는 비어 있을 수 없습니다."
            );
        }
    }
}