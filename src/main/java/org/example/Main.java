package org.example;
import org.example.api.StockApiClient;
import org.example.api.WeatherApiClient;
import org.example.model.*;
import org.example.service.StockAnalysisService;
import org.example.service.StockService;
import org.example.service.ThemeAnalysisService;
import org.example.service.WeatherService;
import org.example.util.CsvUtil;
import org.example.util.RuleReader;
import java.util.List;
import java.util.Map;
import org.example.service.StockCacheService;
import org.example.service.HistoryService;
import org.example.service.MarketDataService;
public class Main {


    public static void main(String[] args) {

        RuleReader.readRules();

        new org.example.view.MainFrame();

        String weatherJson = WeatherApiClient.getWeatherData();
        String airJson = WeatherApiClient.getAirQualityData();

        WeatherData weatherData =
                WeatherApiClient.parseWeatherData(weatherJson, airJson);

        System.out.println("현재 온도: " + weatherData.getTemperature());
        System.out.println("강수량: " + weatherData.getPrecipitation());
        System.out.println("풍속: " + weatherData.getWindspeed());
        System.out.println("미세먼지(pm10): " + weatherData.getPm10());
        System.out.println("날씨 코드: " + weatherData.getWeathercode());

        WeatherService weatherService = new WeatherService();

        WeatherCondition selected =
                weatherService.createConditionByData(weatherData);


        String randomFood =
                RuleReader.getRandomFoodByCondition(
                        selected.getName()
                );

        System.out.println("추천 음식: "
                + randomFood);





        ThemeStrategy themeStrategy;

        if (selected.getThemeName().equals("배달/온라인")) {
            themeStrategy = new DeliveryTheme();
        } else if (selected.getThemeName().equals("여행/소비")) {
            themeStrategy = new TravelTheme();
        } else if (selected.getThemeName().equals("공기청정/위생")) {
            themeStrategy = new AirCareTheme();
        } else if (selected.getThemeName().equals("냉방/전력")) {
            themeStrategy = new CoolingTheme();
        } else {
            themeStrategy = new HeatingTheme();
        }

        StockService stockService = new StockService();

        List<Stock> stocks =
                stockService.getStocksByTheme(themeStrategy);




        MarketDataService marketDataService =
                new MarketDataService();

        List<StockSnapshot> snapshots =
                marketDataService.createSnapshots(
                        stocks
                );





        System.out.println();
        System.out.println("=== 최종 선택 날씨 ===");
        System.out.println("날씨: " + selected.getName());
        System.out.println("추천 테마: " + themeStrategy.getThemeName());
        System.out.println("추천 음식: "
                + randomFood);

        System.out.println();
        System.out.println("=== 추천 종목 TOP 5 ===");


            for (StockSnapshot snapshot : snapshots) {

                System.out.println(
                        snapshot.getStock().getName()
                                + " | 현재가: "
                                + snapshot.getCurrentPrice()
                );
            }
        StockAnalysisService analysisService =
                new StockAnalysisService();

        ThemeAnalysisService themeAnalysisService =
                new ThemeAnalysisService();

        List<ThemeStrategy> themes = List.of(
                new DeliveryTheme(),
                new TravelTheme(),
                new AirCareTheme(),
                new CoolingTheme(),
                new HeatingTheme()
        );

        List<ThemePerformance> performances =
                new java.util.ArrayList<>();

        System.out.println();
        System.out.println("=== 테마별 최근 7일 성과 ===");

        for (ThemeStrategy theme : themes) {

            List<Stock> themeStocks =
                    stockService.getStocksByTheme(theme);

            Map<String, Double> returns =
                    analysisService.calculateReturns(themeStocks);


            System.out.println();
            System.out.println(
                    "[" + theme.getThemeName() + "] 현재가 테스트"
            );


            StockCacheService cacheService =
                    new StockCacheService();

            for (Stock s : themeStocks) {

                String json =
                        StockApiClient.getStockData(
                                s.getSymbol()
                        );

                double current =
                        StockApiClient.parseCurrentPrice(
                                json
                        );


                List<Double> prices =
                        StockApiClient.parseClosePrices(
                                json
                        );

                cacheService.savePrices(
                        s.getSymbol(),
                        prices
                );


                System.out.println(
                        s.getName()
                                + " 현재가 : "
                                + current
                );
            }


            ThemePerformance performance =
                    themeAnalysisService.analyzeTheme(theme, returns);

            performances.add(performance);

            System.out.printf(
                    "%s : %.2f%%\n",
                    performance.getThemeName(),
                    performance.getAverageReturn()
            );
        }

        ThemePerformance bestTheme =
                themeAnalysisService.findBestTheme(performances);

        System.out.println();
        System.out.println("=== 최근 7일 최고 성과 ===");

        System.out.println(
                bestTheme.getThemeName()
                        + " "
                        + String.format("%.2f%%", bestTheme.getAverageReturn())
        );

        System.out.println("최고 종목 : " + bestTheme.getBestStock());
        System.out.println(
                "종목 수익률 : "
                        + String.format("%.2f%%", bestTheme.getBestStockReturn())
        );



        HistoryService historyService =
                new HistoryService();

        historyService.saveHistory(
                selected.getName(),
                themeStrategy.getThemeName(),
                randomFood,
                bestTheme.getThemeName(),
                bestTheme.getAverageReturn()
        );







        CsvUtil.saveResult(selected, themeStrategy, stocks);
    }
}