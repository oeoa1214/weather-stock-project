package org.example.service;

import org.example.model.Class4;

public class run4 {

    private final StockReasonService stockReasonService =
            new StockReasonService();

    public Class4 createClass4(
            Delivery4 delivery4
    ) {
        if (delivery4 == null) {
            throw new IllegalArgumentException(
                    "run4가 받은 Delivery4는 null일 수 없습니다."
            );
        }

        Hub3Data.Item item =
                delivery4.selectedItem();

        String reason =
                stockReasonService.getReason(
                        item.theme(),
                        item.name()
                );

        return new Class4(
                delivery4.currentTheme().getThemeName(),
                item.name(),
                item.currentPrice(),
                item.returnRate(),
                reason
        );
    }
}