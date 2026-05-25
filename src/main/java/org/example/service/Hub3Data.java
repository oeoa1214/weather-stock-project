package org.example.service;

import org.example.model.themeMom;

import java.util.List;

public record Hub3Data(
        themeMom currentTheme,
        List<String> selectableThemes,
        List<Item> items
) {

    public Hub3Data {
        if (currentTheme == null) {
            throw new IllegalArgumentException(
                    "Hub3Data의 currentTheme은 null일 수 없습니다."
            );
        }

        if (selectableThemes == null || selectableThemes.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hub3Data의 selectableThemes는 비어 있을 수 없습니다."
            );
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hub3Data의 items는 비어 있을 수 없습니다."
            );
        }

        selectableThemes =
                List.copyOf(selectableThemes);

        items =
                List.copyOf(items);
    }

    public record Item(
            String name,
            String symbol,
            String theme,
            double currentPrice,
            double returnRate
    ) {

        public Item {
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

            if (currentPrice < 0) {
                throw new IllegalArgumentException(
                        "현재가는 음수가 될 수 없습니다."
                );
            }
        }
    }
}