package org.example.model;

import java.util.List;

public class TravelTheme extends ThemeStrategy {

    public TravelTheme() {
        super("여행/소비");
    }

    @Override
    public List<Stock> getRecommendedStocks() {

        return List.of(

                new Stock("대한항공",
                        "003490.KS",
                        getThemeName(),
                        21000,
                        1.2),

                new Stock("하나투어",
                        "039130.KS",
                        getThemeName(),
                        52000,
                        0.8),

                new Stock("호텔신라",
                        "008770.KS",
                        getThemeName(),
                        65000,
                        1.5),

                new Stock("CJ CGV",
                        "079160.KS",
                        getThemeName(),
                        12000,
                        -0.5),

                new Stock("신세계",
                        "004170.KS",
                        getThemeName(),
                        230000,
                        0.9)
        );
    }
}