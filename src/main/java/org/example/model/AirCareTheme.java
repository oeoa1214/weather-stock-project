package org.example.model;

import java.util.List;

public class AirCareTheme extends ThemeStrategy {

    public AirCareTheme() {
        super("공기청정/위생");
    }

    @Override
    public List<Stock> getRecommendedStocks() {

        return List.of(

                new Stock(
                        "위닉스",
                        "044340.KQ",
                        getThemeName(),
                        9000,
                        1.1
                ),

                new Stock(
                        "코웨이",
                        "021240.KS",
                        getThemeName(),
                        60000,
                        0.5
                ),

                new Stock(
                        "LG전자",
                        "066570.KS",
                        getThemeName(),
                        128500,
                        1.85
                ),

                new Stock(
                        "케이엠",
                        "083550.KQ",
                        getThemeName(),
                        7000,
                        0.7
                ),

                new Stock(
                        "깨끗한나라",
                        "004540.KS",
                        getThemeName(),
                        3000,
                        0.3
                )
        );
    }
}