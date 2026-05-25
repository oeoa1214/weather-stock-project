package org.example.service;

import java.util.List;

public record Delivery7(
        String status,
        String themeName,
        List<Line> lines
) {

    public record Line(
            String stockName,
            String symbol,
            String theme,
            double startPrice,
            double currentPrice,
            double lastChange,
            double totalChange,
            double returnRate,
            int pendingCount
    ) {
    }
}