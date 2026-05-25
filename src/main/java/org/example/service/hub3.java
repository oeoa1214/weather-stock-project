package org.example.service;

import org.example.model.themeMom;

import java.util.List;

public class hub3 {

    private themeMom currentTheme;
    private List<Hub3Data.Item> items;

    public void receiveThemeFromThemeMom(
            themeMom currentTheme
    ) {
        if (currentTheme == null) {
            throw new IllegalArgumentException(
                    "hub3가 받은 themeMom은 null일 수 없습니다."
            );
        }

        this.currentTheme =
                currentTheme;
    }

    public void receiveItems(
            List<Hub3Data.Item> items
    ) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(
                    "hub3가 받은 현재가 결과 목록은 비어 있을 수 없습니다."
            );
        }

        this.items =
                List.copyOf(items);
    }

    public Hub3Data createHub3Data() {
        if (currentTheme == null) {
            throw new IllegalStateException(
                    "hub3에 themeMom 결과가 없습니다."
            );
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalStateException(
                    "hub3에 현재가 결과 목록이 없습니다."
            );
        }

        return new Hub3Data(
                currentTheme,
                createSelectableThemes(
                        currentTheme.getThemeName()
                ),
                items
        );
    }

    private List<String> createSelectableThemes(
            String themeName
    ) {
        return switch (themeName) {

            case "여행/소비" ->
                    List.of(
                            "여행/소비",
                            "항공·이동",
                            "여행예약",
                            "호텔·면세",
                            "영화·문화생활",
                            "백화점·유통"
                    );

            case "배달/온라인" ->
                    List.of(
                            "배달/온라인",
                            "편의점·근거리 소비",
                            "간편식·식품"
                    );

            case "공기청정/위생" ->
                    List.of(
                            "공기청정/위생",
                            "공기청정가전",
                            "위생소모품",
                            "생활위생용품"
                    );

            case "냉방/전력" ->
                    List.of(
                            "냉방/전력",
                            "냉방가전",
                            "전력수요",
                            "여름음료·생수",
                            "여름음료·빙과"
                    );

            case "난방/에너지" ->
                    List.of(
                            "난방/에너지",
                            "도시가스·난방",
                            "정유·연료",
                            "정유·에너지",
                            "보일러·난방기기",
                            "겨울간식·식품"
                    );

            default ->
                    List.of(
                            themeName
                    );
        };
    }
}