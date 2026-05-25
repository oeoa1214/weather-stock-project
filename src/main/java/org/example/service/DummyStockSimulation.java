package org.example.service;

import org.example.model.CloseInfo;
import org.example.model.Stock;
import org.example.model.weatherMom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyStockSimulation {

    private static final double MIN_RETURN_RATE =
            -10.0;

    private static final double MAX_RETURN_RATE =
            10.0;

    private static final int DAYS =
            7;

    private final Random random =
            new Random();

    /*
     * 기본 더미 주식 생성
     * 날씨 적중 테마 보정은 0~2%로 둔다.
     */
    public DummyStockResult createDummyStockResult(
            List<Stock> stocks,
            weatherMom currentWeather
    ) {
        return createDummyStockResult(
                stocks,
                currentWeather,
                2.0
        );
    }

    /*
     * 보정치를 외부에서 조절할 수 있는 더미 주식 생성
     * bonusMaxRate = 0, 1, 2, 3 같은 값
     */
    public DummyStockResult createDummyStockResult(
            List<Stock> stocks,
            weatherMom currentWeather,
            double bonusMaxRate
    ) {
        if (stocks == null || stocks.isEmpty()) {
            throw new IllegalArgumentException(
                    "더미 생성용 종목 목록은 비어 있을 수 없습니다."
            );
        }

        if (currentWeather == null) {
            throw new IllegalArgumentException(
                    "현재 날씨는 null일 수 없습니다."
            );
        }

        if (bonusMaxRate < 0.0 || bonusMaxRate > 3.0) {
            throw new IllegalArgumentException(
                    "날씨 보정치는 0~3 사이여야 합니다."
            );
        }

        String boostedTheme =
                decideBoostedTheme(
                        currentWeather.getName()
                );

        List<Hub3Data.Item> hub3Items =
                new ArrayList<>();

        List<CloseInfo> closeInfos =
                new ArrayList<>();

        for (Stock stock : stocks) {

            double startPrice =
                    createStartPrice();

            double returnRate =
                    createReturnRate(
                            stock,
                            boostedTheme,
                            bonusMaxRate
                    );

            List<Double> closePrices =
                    createClosePrices(
                            startPrice,
                            returnRate
                    );

            double currentPrice =
                    closePrices.get(
                            closePrices.size() - 1
                    );

            hub3Items.add(
                    new Hub3Data.Item(
                            stock.getName(),
                            stock.getSymbol(),
                            stock.getTheme(),
                            currentPrice,
                            returnRate
                    )
            );

            closeInfos.add(
                    new CloseInfo(
                            stock,
                            closePrices
                    )
            );
        }

        return new DummyStockResult(
                hub3Items,
                closeInfos,
                currentWeather.getName(),
                boostedTheme
        );
    }

    private double createStartPrice() {
        return 5000
                + random.nextInt(
                200000
        );
    }

    private double createReturnRate(
            Stock stock,
            String boostedTheme,
            double bonusMaxRate
    ) {
        double baseReturn =
                randomBetween(
                        MIN_RETURN_RATE,
                        MAX_RETURN_RATE
                );

        if (stock.getTheme().equals(boostedTheme)) {
            double bonus =
                    randomBetween(
                            0.0,
                            bonusMaxRate
                    );

            baseReturn +=
                    bonus;
        }

        return clamp(
                baseReturn,
                MIN_RETURN_RATE,
                MAX_RETURN_RATE
        );
    }

    private List<Double> createClosePrices(
            double startPrice,
            double totalReturnRate
    ) {
        List<Double> closePrices =
                new ArrayList<>();

        double endPrice =
                startPrice
                        * (1.0 + totalReturnRate / 100.0);

        for (int i = 0; i < DAYS; i++) {
            double ratio =
                    (double) i / (DAYS - 1);

            double noise =
                    randomBetween(
                            -0.015,
                            0.015
                    );

            double price =
                    startPrice
                            + (endPrice - startPrice) * ratio;

            price =
                    price * (1.0 + noise);

            closePrices.add(
                    round(price)
            );
        }

        closePrices.set(
                0,
                round(startPrice)
        );

        closePrices.set(
                closePrices.size() - 1,
                round(endPrice)
        );

        return closePrices;
    }

    private String decideBoostedTheme(
            String weatherName
    ) {
        return switch (weatherName) {
            case "비" ->
                    "편의점·간편식";

            case "맑음" ->
                    "여행·소비";

            case "미세먼지" ->
                    "공기청정·위생";

            case "폭염" ->
                    "냉방·여름소비";

            case "한파" ->
                    "난방·겨울소비";

            default ->
                    throw new IllegalArgumentException(
                            "5개 날씨 테마에 없는 날씨입니다: " + weatherName
                    );
        };
    }

    private double randomBetween(
            double min,
            double max
    ) {
        return min
                + random.nextDouble() * (max - min);
    }

    private double clamp(
            double value,
            double min,
            double max
    ) {
        if (value < min) {
            return min;
        }

        if (value > max) {
            return max;
        }

        return value;
    }

    private double round(
            double value
    ) {
        return Math.round(value * 100.0) / 100.0;
    }

    public record DummyStockResult(
            List<Hub3Data.Item> hub3Items,
            List<CloseInfo> closeInfos,
            String weatherName,
            String boostedTheme
    ) {

        public DummyStockResult {
            if (hub3Items == null || hub3Items.isEmpty()) {
                throw new IllegalArgumentException(
                        "hub3Items는 비어 있을 수 없습니다."
                );
            }

            if (closeInfos == null || closeInfos.isEmpty()) {
                throw new IllegalArgumentException(
                        "closeInfos는 비어 있을 수 없습니다."
                );
            }

            if (weatherName == null || weatherName.isBlank()) {
                throw new IllegalArgumentException(
                        "weatherName은 비어 있을 수 없습니다."
                );
            }

            if (boostedTheme == null || boostedTheme.isBlank()) {
                throw new IllegalArgumentException(
                        "boostedTheme은 비어 있을 수 없습니다."
                );
            }

            hub3Items =
                    List.copyOf(
                            hub3Items
                    );

            closeInfos =
                    List.copyOf(
                            closeInfos
                    );
        }
    }
}