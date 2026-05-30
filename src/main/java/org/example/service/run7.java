package org.example.service;

import org.example.model.Class7;

import java.util.ArrayList;
import java.util.List;

public class run7 {

    public List<Class7> createClass7List(
            List<Hub3Data.Item> hub3Items
    ) {
        if (hub3Items == null || hub3Items.isEmpty()) {
            throw new IllegalArgumentException(
                    "hub3Items는 비어 있을 수 없습니다."
            );
        }

        List<Class7> result =
                new ArrayList<>();

        addRepresentativeStock(
                result,
                hub3Items,
                "편의점·간편식",
                "BGF리테일"
        );

        addRepresentativeStock(
                result,
                hub3Items,
                "여행·소비",
                "대한항공"
        );

        addRepresentativeStock(
                result,
                hub3Items,
                "공기청정·위생",
                "위닉스"
        );

        addRepresentativeStock(
                result,
                hub3Items,
                "냉방·여름소비",
                "LG전자"
        );

        addRepresentativeStock(
                result,
                hub3Items,
                "난방·겨울소비",
                "한국가스공사"
        );

        if (result.size() != 5) {
            throw new IllegalStateException(
                    "Class7 대표 종목은 반드시 5개여야 합니다. 현재 개수: "
                            + result.size()
            );
        }

        return result;
    }

    private void addRepresentativeStock(
            List<Class7> result,
            List<Hub3Data.Item> hub3Items,
            String themeName,
            String targetStockName
    ) {
        for (Hub3Data.Item item : hub3Items) {
            if (item.name().equals(targetStockName)) {
                result.add(
                        new Class7(
                                themeName,
                                item.name(),
                                item.currentPrice(),
                                item.returnRate()
                        )
                );

                return;
            }
        }

        throw new IllegalStateException(
                "대표 종목을 찾을 수 없습니다: " + targetStockName
        );
    }
}