package org.example.model;

import java.util.List;

public class DeliveryTheme extends ThemeStrategy {

    public DeliveryTheme() {
        super("배달/온라인");
    }

    @Override
    public List<Stock> getRecommendedStocks() {

        return List.of(

                new Stock(
                        "BGF리테일",
                        "282330.KS",
                        getThemeName(),
                        128500,
                        1.85
                ),

                new Stock(
                        "GS리테일",
                        "007070.KS",
                        getThemeName(),
                        21300,
                        0.92
                ),

                new Stock(
                        "쿠팡",
                        "CPNG",
                        getThemeName(),
                        24850,
                        2.31
                ),

                new Stock(
                        "오뚜기",
                        "007310.KS",
                        getThemeName(),
                        412000,
                        0.63
                ),

                new Stock(
                        "CJ대한통운",
                        "000120.KS",
                        getThemeName(),
                        120400,
                        1.27
                )
        );
    }
}