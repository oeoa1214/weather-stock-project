package org.example.service;

import org.example.model.themeMom;

import java.util.List;

public record Delivery3(
        themeMom currentTheme,
        List<Hub3Data.Item> selectedItems
) {

    public Delivery3 {
        if (currentTheme == null) {
            throw new IllegalArgumentException(
                    "Delivery3의 currentTheme은 null일 수 없습니다."
            );
        }

        if (selectedItems == null || selectedItems.isEmpty()) {
            throw new IllegalArgumentException(
                    "Delivery3의 selectedItems는 비어 있을 수 없습니다."
            );
        }

        selectedItems =
                List.copyOf(selectedItems);
    }
}