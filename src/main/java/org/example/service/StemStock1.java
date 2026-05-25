package org.example.service;

import org.example.model.CloseInfo;
import org.example.model.RealtimeInfo;
import org.example.model.Stock;

import java.util.List;

public class StemStock1 {

    private final Stock stock;
    private final double currentPrice;
    private final List<Double> closePrices;

    public StemStock1(
            Stock stock,
            double currentPrice,
            List<Double> closePrices
    ) {
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

        if (closePrices == null || closePrices.isEmpty()) {
            throw new IllegalArgumentException(
                    "최근 7일 종가 목록은 비어 있을 수 없습니다."
            );
        }

        this.stock =
                stock;

        this.currentPrice =
                currentPrice;

        this.closePrices =
                List.copyOf(closePrices);
    }

    public RealtimeInfo sendToRealtimeInfo() {
        return new RealtimeInfo(
                stock,
                currentPrice,
                calculateReturnRate()
        );
    }

    public CloseInfo sendToCloseInfo() {
        return new CloseInfo(
                stock,
                closePrices
        );
    }

    private double calculateReturnRate() {
        if (closePrices.size() < 2) {
            return 0;
        }

        double first =
                closePrices.get(0);

        double last =
                closePrices.get(
                        closePrices.size() - 1
                );

        if (first == 0) {
            return 0;
        }

        return ((last - first) / first) * 100;
    }
}