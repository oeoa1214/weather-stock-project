package org.example.service;

import java.util.ArrayList;
import java.util.List;

public class Judge3 {

    public Delivery3 judge(
            Hub3Data hub3Data
    ) {
        if (hub3Data == null) {
            throw new IllegalArgumentException(
                    "Judge3가 받은 Hub3Data는 null일 수 없습니다."
            );
        }

        List<Hub3Data.Item> selectedItems =
                new ArrayList<>();

        for (Hub3Data.Item item : hub3Data.items()) {
            if (hub3Data.selectableThemes().contains(item.theme())) {
                selectedItems.add(
                        item
                );
            }

            if (selectedItems.size() == 5) {
                break;
            }
        }

        if (selectedItems.isEmpty()) {
            throw new IllegalStateException(
                    "현재 테마에 맞는 3층 주식 정보가 없습니다: "
                            + hub3Data.currentTheme().getThemeName()
            );
        }

        return new Delivery3(
                hub3Data.currentTheme(),
                selectedItems
        );
    }
}