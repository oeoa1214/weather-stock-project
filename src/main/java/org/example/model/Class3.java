package org.example.model;

public record Class3(
        String name,
        String symbol,
        String theme,
        double currentPrice,
        double returnRate,
        String reason
) {

    public Class3 {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "종목명은 비어 있을 수 없습니다."
            );
        }

        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException(
                    "심볼은 비어 있을 수 없습니다."
            );
        }

        if (theme == null || theme.isBlank()) {
            throw new IllegalArgumentException(
                    "테마는 비어 있을 수 없습니다."
            );
        }

        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException(
                    "추천 이유는 비어 있을 수 없습니다."
            );
        }
    }
}