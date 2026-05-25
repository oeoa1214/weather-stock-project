package org.example.service;

import org.example.model.Class7;
import org.example.model.CloseInfo;

import java.util.ArrayList;
import java.util.List;

public class run7 {

    private static final int CLASS7_STOCK_COUNT =
            5;

    public Class7 createClass7(
            String themeName,
            List<CloseInfo> closeInfos
    ) {
        List<Class7.Row> rows =
                new ArrayList<>();

        for (CloseInfo closeInfo : closeInfos) {
            if (!isSameTheme(
                    closeInfo.stock().getTheme(),
                    themeName
            )) {
                continue;
            }

            List<Double> closePrices =
                    closeInfo.closePrices();

            if (closePrices == null || closePrices.isEmpty()) {
                continue;
            }

            double closePrice =
                    closePrices.get(
                            closePrices.size() - 1
                    );

            rows.add(
                    new Class7.Row(
                            closeInfo.stock().getName(),
                            closePrice
                    )
            );

            if (rows.size() == CLASS7_STOCK_COUNT) {
                break;
            }
        }

        if (rows.size() != CLASS7_STOCK_COUNT) {
            throw new IllegalStateException(
                    "Class7은 종목 5개가 필요합니다. 현재 개수: "
                            + rows.size()
            );
        }

        return new Class7(
                rows
        );
    }

    private boolean isSameTheme(
            String stockTheme,
            String themeName
    ) {
        if (stockTheme.equals(themeName)) {
            return true;
        }

        if (themeName.equals("배달/온라인")) {
            return stockTheme.equals("배달/온라인")
                    || stockTheme.equals("편의점·근거리 소비")
                    || stockTheme.equals("간편식·식품");
        }

        if (themeName.equals("여행/소비")) {
            return stockTheme.equals("여행/소비")
                    || stockTheme.equals("항공·이동")
                    || stockTheme.equals("여행예약")
                    || stockTheme.equals("호텔·면세")
                    || stockTheme.equals("영화·문화생활")
                    || stockTheme.equals("백화점·유통");
        }

        if (themeName.equals("냉방/전력")) {
            return stockTheme.equals("냉방/전력")
                    || stockTheme.equals("냉방가전")
                    || stockTheme.equals("전력수요")
                    || stockTheme.equals("여름음료·생수")
                    || stockTheme.equals("여름음료·빙과");
        }

        if (themeName.equals("난방/에너지")) {
            return stockTheme.equals("난방/에너지")
                    || stockTheme.equals("도시가스·난방")
                    || stockTheme.equals("정유·연료")
                    || stockTheme.equals("정유·에너지")
                    || stockTheme.equals("보일러·난방기기")
                    || stockTheme.equals("겨울간식·식품");
        }

        if (themeName.equals("공기청정/위생")) {
            return stockTheme.equals("공기청정/위생")
                    || stockTheme.equals("공기청정가전")
                    || stockTheme.equals("위생소모품")
                    || stockTheme.equals("생활위생용품");
        }

        return false;
    }
}