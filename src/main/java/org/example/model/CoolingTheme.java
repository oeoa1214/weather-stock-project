package org.example.model;

import java.util.List;

public class CoolingTheme extends ThemeStrategy {

    public CoolingTheme() {
        super("냉방/전력");
    }

    @Override
    public List<Stock> getRecommendedStocks() {

        return List.of(

                new Stock(
                        "LG전자",
                        "066570.KS",
                        getThemeName(),
                        128500,
                        1.85
                ),

                new Stock(
                        "삼성전자",
                        "005930.KS",
                        getThemeName(),
                        70000,
                        1.2
                ),

                new Stock(
                        "한국전력",
                        "015760.KS",
                        getThemeName(),
                        20000,
                        0.9
                ),

                new Stock(
                        "롯데칠성",
                        "005300.KS",
                        getThemeName(),
                        180000,
                        1.1
                ),

                new Stock(
                        "빙그레",
                        "005180.KS",
                        getThemeName(),
                        50000,
                        0.7
                )
        );
    }
}