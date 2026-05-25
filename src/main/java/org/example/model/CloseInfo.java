package org.example.model;

import java.util.List;

public record CloseInfo(
        Stock stock,
        List<Double> closePrices
) {

    public CloseInfo {
        if (stock == null) {
            throw new IllegalArgumentException(
                    "Stock은 null일 수 없습니다."
            );
        }

        if (closePrices == null || closePrices.isEmpty()) {
            throw new IllegalArgumentException(
                    "최근 종가 목록은 비어 있을 수 없습니다."
            );
        }

        closePrices =
                List.copyOf(closePrices);
    }
}