package org.example.service;

public class Judge4 {

    public Delivery4 judge(
            Delivery3 delivery3
    ) {
        if (delivery3 == null) {
            throw new IllegalArgumentException(
                    "Judge4가 받은 Delivery3는 null일 수 없습니다."
            );
        }

        Hub3Data.Item best =
                delivery3.selectedItems().get(0);

        for (Hub3Data.Item item : delivery3.selectedItems()) {
            if (item.returnRate() > best.returnRate()) {
                best =
                        item;
            }
        }

        return new Delivery4(
                delivery3.currentTheme(),
                best
        );
    }
}