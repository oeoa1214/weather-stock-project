package org.example.model;

import java.util.List;

public record Class7(
        List<Row> rows
) {
    public Class7 {
        if (rows == null || rows.size() != 5) {
            throw new IllegalArgumentException(
                    "Class7은 종목 5개가 필요합니다."
            );
        }

        rows = List.copyOf(rows);
    }

    public record Row(
            String stockName,
            double closePrice
    ) {
        public Row {
            if (stockName == null || stockName.isBlank()) {
                throw new IllegalArgumentException(
                        "종목명은 비어 있을 수 없습니다."
                );
            }

            if (closePrice <= 0) {
                throw new IllegalArgumentException(
                        "종가는 0보다 커야 합니다."
                );
            }
        }
    }
}