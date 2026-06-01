package org.example.controller;

import org.example.model.CloseInfo;
import org.example.service.Hub3Data;

import java.util.List;

public record StockControllerData(
        List<Hub3Data.Item> hub3Items,
        List<CloseInfo> closeInfos
) {

    public StockControllerData {

        if (hub3Items == null || hub3Items.isEmpty()) {
            throw new IllegalArgumentException(
                    "hub3Items는 비어 있을 수 없습니다."
            );
        }

        if (closeInfos == null || closeInfos.isEmpty()) {
            throw new IllegalArgumentException(
                    "closeInfos는 비어 있을 수 없습니다."
            );
        }

        hub3Items =
                List.copyOf(
                        hub3Items
                );

        closeInfos =
                List.copyOf(
                        closeInfos
                );
    }
}