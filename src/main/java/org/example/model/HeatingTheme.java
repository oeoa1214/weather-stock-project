package org.example.model;

import java.util.List;

public class HeatingTheme extends ThemeStrategy {

    public HeatingTheme() {
        super("난방/에너지");
    }

    @Override
    public List<Stock> getRecommendedStocks() {

        return List.of(

                new Stock(
                        "한국가스공사",
                        "036460.KS",
                        getThemeName(),
                        40000,
                        1.3
                ),

                new Stock(
                        "S-Oil",
                        "010950.KS",
                        getThemeName(),
                        70000,
                        1.1
                ),

                new Stock(
                        "SK이노베이션",
                        "096770.KS",
                        getThemeName(),
                        120000,
                        1.0
                ),

                new Stock(
                        "경동나비엔",
                        "009450.KS",
                        getThemeName(),
                        30000,
                        0.8
                ),

                new Stock(
                        "롯데웰푸드",
                        "280360.KS",
                        getThemeName(),
                        150000,
                        0.6
                )
        );
    }
}