package org.example.service;

import org.example.model.themeMom;

public record Delivery4(
        themeMom currentTheme,
        Hub3Data.Item selectedItem
) {

    public Delivery4 {
        if (currentTheme == null) {
            throw new IllegalArgumentException(
                    "Delivery4의 currentTheme은 null일 수 없습니다."
            );
        }

        if (selectedItem == null) {
            throw new IllegalArgumentException(
                    "Delivery4의 selectedItem은 null일 수 없습니다."
            );
        }
    }
}