package org.example.controller;

import org.example.model.*;
import org.example.service.*;
import org.example.util.RuleReader;
import java.util.List;
import java.util.Map;

public class AppController {

    public void run() {

        RuleReader.readRules();

        WeatherDecisionService weatherDecisionService =
                new WeatherDecisionService();

        WeatherDecision weatherDecision =
                weatherDecisionService.decideWeather();

        WeatherData weatherData =
                weatherDecision.weatherData();

        WeatherCondition selected =
                weatherDecision.condition();

        System.out.println();
        System.out.println("=== 날씨 원본 데이터 확인 ===");
        System.out.println("온도: " + weatherData.temperature());
        System.out.println("강수량: " + weatherData.precipitation());
        System.out.println("풍속: " + weatherData.windspeed());
        System.out.println("미세먼지(pm10): " + weatherData.pm10());
        System.out.println("날씨 코드: " + weatherData.weathercode());

        FoodInfoService foodInfoService =
                new FoodInfoService();

        FoodInfo foodInfo =
                foodInfoService.createFoodInfo(
                        selected.getName()
                );

        String randomFood =
                foodInfo.foodName();

        System.out.println();
        System.out.println("=== 오늘의 추천 음식 ===");
        System.out.println("날씨: " + foodInfo.weatherName());
        System.out.println("음식: " + foodInfo.foodName());
        System.out.println("설명: " + foodInfo.description());
        System.out.println("태그: " + foodInfo.tags());
        System.out.println("추천 이유: " + foodInfo.reasons());

        ThemeRecommendService themeRecommendService =
                new ThemeRecommendService();

        ThemeStrategy themeStrategy =
                themeRecommendService.recommendTheme(
                        selected
                );

        StockService stockService =
                new StockService();

        MarketDataService marketDataService =
                new MarketDataService();

        ReturnMapService returnMapService =
                new ReturnMapService();

        ThemeAnalysisService themeAnalysisService =
                new ThemeAnalysisService();

        BestThemeService bestThemeService =
                new BestThemeService();

        StockCacheService cacheService =
                new StockCacheService();

        StockPriceService priceService =
                new StockPriceService();

        StockPrinterService stockPrinterService =
                new StockPrinterService();

        WeatherPrinterService weatherPrinterService =
                new WeatherPrinterService();

        RecommendedStockService recommendedStockService =
                new RecommendedStockService();

        List<Stock> stocks =
                stockService.getStocksByTheme(
                        themeStrategy
                );

        List<StockSnapshot> snapshots =
                marketDataService.createSnapshots(
                        stocks
                );

        RecommendedStockInfo recommendedStockInfo =
                recommendedStockService.createRecommendedStockInfo(
                        themeStrategy,
                        snapshots
                );

        System.out.println();
        System.out.println("=== 오늘의 추천 종목 ===");

        System.out.println(
                "테마: "
                        + recommendedStockInfo.themeName()
        );

        System.out.println(
                "종목: "
                        + recommendedStockInfo.stockName()
        );

        System.out.println(
                "현재가: "
                        + recommendedStockInfo.currentPrice()
        );

        System.out.println(
                "수익률: "
                        + String.format(
                        "%.2f%%",
                        recommendedStockInfo.returnRate()
                )
        );

        System.out.println(
                "추천 이유: "
                        + recommendedStockInfo.reason()
        );

        weatherPrinterService.printSelectedWeather(
                selected,
                themeStrategy,
                randomFood
        );

        stockPrinterService.printSnapshots(
                snapshots
        );

        for (StockSnapshot snapshot : snapshots) {

            String json =
                    priceService.getStockJson(
                            snapshot.getStock()
                    );

            cacheService.cacheStock(
                    snapshot.getStock(),
                    json
            );
        }

        List<ThemeStrategy> themes =
                List.of(
                        new DeliveryTheme(),
                        new TravelTheme(),
                        new AirCareTheme(),
                        new CoolingTheme(),
                        new HeatingTheme()
                );

        List<ThemePerformance> performances =
                new java.util.ArrayList<>();

        for (ThemeStrategy theme : themes) {

            List<Stock> themeStocks =
                    stockService.getStocksByTheme(
                            theme
                    );

            List<StockSnapshot> themeSnapshots =
                    marketDataService.createSnapshots(
                            themeStocks
                    );

            Map<String, Double> returns =
                    returnMapService.createReturnMap(
                            themeSnapshots
                    );

            ThemePerformance performance =
                    themeAnalysisService.analyzeTheme(
                            theme,
                            returns
                    );

            performances.add(performance);
        }

        stockPrinterService.printThemePerformances(
                performances
        );

        ThemePerformance bestTheme =
                bestThemeService.findBestTheme(
                        performances
                );

        stockPrinterService.printBestTheme(
                bestTheme
        );

        HistoryService historyService =
                new HistoryService();

        historyService.saveHistory(
                selected.getName(),
                themeStrategy.getThemeName(),
                randomFood,
                bestTheme.themeName(),
                bestTheme.averageReturn()
        );

        new org.example.view.MainFrame();
    }
}