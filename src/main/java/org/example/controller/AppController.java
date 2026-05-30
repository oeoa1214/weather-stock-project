package org.example.controller;

import org.example.view.MainFrame;
import org.example.model.*;
import org.example.service.*;
import org.example.util.StockFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppController {

    private final DummyModeController dummyModeController =
            new DummyModeController();

    private final KospiWeatherAverageController kospiWeatherAverageController =
            new KospiWeatherAverageController();

    private final KospiTemperatureAverageController kospiTemperatureAverageController =
            new KospiTemperatureAverageController();

    private final run5 run5Service =
            new run5();

    private final run6 run6Service =
            new run6();

    private boolean dummyMode =
            false;

    private boolean kospiAverageMode =
            false;

    private boolean kospiTemperatureMode =
            false;

    private final ViewRun6 viewRun6 =
            new ViewRun6();

    private final RecommendRefreshService recommendRefreshService =
            new RecommendRefreshService();

    private List<Hub3Data.Item> lastHub3Items =
            List.of();

    public void run() {

        Stem1 stem1 =
                new Stem1();

        Stem2 stem2 =
                new Stem2();

        Judge2 judge2 =
                new Judge2();

        run1 run1 =
                new run1();

        stem2.receiveFromStem1(
                stem1.sendToStem2()
        );

        weatherMom judgedWeather =
                judge2.judge(
                        stem2.sendTemperatureToJudge2(),
                        stem2.sendPrecipitationToJudge2(),
                        stem2.sendPm10ToJudge2()
                );

        stem2.receiveFromJudge2(
                judgedWeather
        );

        Class1 class1 =
                run1.createClass1(
                        stem2,
                        judgedWeather
                );

        themeMom theme =
                createThemeByWeather(
                        judgedWeather
                );

        List<Stock> allStocks =
                createAllStocks();

        StockPreparedResult stockPreparedResult =
                createStockPreparedResult(
                        allStocks
                );

        List<Hub3Data.Item> hub3Items =
                stockPreparedResult.hub3Items();

        List<CloseInfo> closeInfos =
                stockPreparedResult.closeInfos();

        lastHub3Items =
                hub3Items;

        run7 run7 =
                new run7();

        List<Class7> class7Result =
                run7.createClass7List(
                        hub3Items
                );

        hub3 hub3 =
                new hub3();

        hub3.receiveThemeFromThemeMom(
                theme
        );

        hub3.receiveItems(
                hub3Items
        );

        Hub3Data hub3Data =
                hub3.createHub3Data();

        Judge3 judge3 =
                new Judge3();

        Delivery3 delivery3 =
                judge3.judge(
                        hub3Data
                );

        Stem3 stem3 =
                new Stem3();

        stem3.receiveFromDelivery3(
                delivery3
        );

        run3 run3 =
                new run3();

        List<Class3> class3Result =
                run3.createClass3List(
                        stem3.sendToRun3()
                );

        Stem4 stem4 =
                new Stem4();

        stem4.receiveFromStem3(
                stem3.sendToStem4()
        );

        Judge4 judge4 =
                new Judge4();

        Delivery4 delivery4 =
                judge4.judge(
                        stem4.sendToJudge4()
                );

        run4 run4 =
                new run4();

        Class4 class4 =
                run4.createClass4(
                        delivery4
                );

        Class5 class5Result =
                run5Service.createClass5ByCloseInfo(
                        closeInfos
                );

        Class6 class6Result =
                run6Service.createClass6ByCloseInfo(
                        closeInfos
                );

        run9 run9Service =
                new run9();

        Class9 class9 =
                run9Service.createClass9(
                        judgedWeather.getName()
                );

        new MainFrame(
                class1,
                class4,
                class3Result,
                class5Result,
                class6Result,
                class7Result,
                class9,
                this
        );
    }

    public void startDummyMode() {
        startDummyMode(
                "data/daegu_weather.csv"
        );
    }

    public void startDummyMode(
            String filePath
    ) {
        dummyMode =
                true;

        dummyModeController.start(
                filePath
        );

        run5Service.resetDummy();

        run6Service.resetDummy();
    }

    public DummyStepResult runDummyOneDay(
            int bonusLevel
    ) {
        if (!dummyMode) {
            throw new IllegalStateException(
                    "더미 모드가 시작되지 않았습니다. startDummyMode()를 먼저 호출해야 합니다."
            );
        }

        if (dummyModeController.isFinished()) {
            dummyMode =
                    false;

            return new DummyStepResult(
                    true,
                    365,
                    365,
                    "완료",
                    "완료",
                    run5Service.getClass5(),
                    run6Service.getClass6()
            );
        }

        DummyModeController.DummyInput dummyInput =
                dummyModeController.nextDay(
                        bonusLevel
                );

        Map<String, Double> dailyThemeAverageReturns =
                dummyInput.dailyThemeAverageReturns();

        Class5 class5Result =
                run5Service.createClass5ByDummy(
                        dailyThemeAverageReturns
                );

        Class6 class6Result =
                run6Service.createClass6ByDummy(
                        dailyThemeAverageReturns
                );

        boolean finished =
                dummyModeController.isFinished();

        if (finished) {
            dummyMode =
                    false;
        }

        return new DummyStepResult(
                finished,
                dummyInput.currentDay(),
                dummyInput.totalDay(),
                dummyInput.weatherName(),
                dummyInput.themeName(),
                class5Result,
                class6Result
        );
    }

    public void stopDummyMode() {
        dummyMode =
                false;
    }

    public boolean isDummyMode() {
        return dummyMode;
    }

    public boolean isDummyModeFinished() {
        return dummyModeController.isFinished();
    }

    public record DummyStepResult(
            boolean finished,
            int currentDay,
            int totalDay,
            String weatherName,
            String themeName,
            Class5 class5Result,
            Class6 class6Result
    ) {
    }

    public void startKospiAverageMode() {
        kospiAverageMode =
                true;

        kospiWeatherAverageController.start(
                "data/kospi_weather_2025.csv"
        );

        run5Service.resetDummy();

        run6Service.resetDummy();
    }

    public KospiAverageStepResult runKospiAverageOneDay() {
        if (!kospiAverageMode) {
            throw new IllegalStateException(
                    "KOSPI 평균 모드가 시작되지 않았습니다."
            );
        }

        if (kospiWeatherAverageController.isFinished()) {
            kospiAverageMode =
                    false;

            return new KospiAverageStepResult(
                    true,
                    kospiWeatherAverageController.getCurrentDay(),
                    kospiWeatherAverageController.getTotalDay(),
                    "완료",
                    run5Service.getClass5(),
                    run6Service.getClass6(),
                    kospiWeatherAverageController.getProgressData()
            );
        }

        Map<String, Double> averageReturnMap =
                kospiWeatherAverageController.nextDay();

        Map<String, Double> dailyThemeReturnMap =
                kospiWeatherAverageController.getLastDailyThemeReturnMap();

        Class5 class5Result =
                run5Service.createClass5ByDummy(
                        dailyThemeReturnMap
                );

        run6Service.resetDummy();

        Class6 class6Result =
                run6Service.createClass6ByDummy(
                        averageReturnMap
                );

        boolean finished =
                kospiWeatherAverageController.isFinished();

        if (finished) {
            kospiAverageMode =
                    false;
        }

        return new KospiAverageStepResult(
                finished,
                kospiWeatherAverageController.getCurrentDay(),
                kospiWeatherAverageController.getTotalDay(),
                kospiWeatherAverageController.getCurrentDate(),
                class5Result,
                class6Result,
                kospiWeatherAverageController.getProgressData()
        );
    }

    public void stopKospiAverageMode() {
        kospiAverageMode =
                false;
    }

    public boolean isKospiAverageMode() {
        return kospiAverageMode;
    }

    public record KospiAverageStepResult(
            boolean finished,
            int currentDay,
            int totalDay,
            String date,
            Class5 class5Result,
            Class6 class6Result,
            AnalysisProgressData progressData
    ) {
    }

    public void startKospiTemperatureMode() {
        kospiTemperatureMode =
                true;

        kospiTemperatureAverageController.start(
                "data/kospi_weather_2025.csv"
        );

        run6Service.resetDummy();
    }

    public KospiTemperatureStepResult runKospiTemperatureOneDay() {
        if (!kospiTemperatureMode) {
            throw new IllegalStateException(
                    "KOSPI 기온 분석 모드가 시작되지 않았습니다."
            );
        }

        if (kospiTemperatureAverageController.isFinished()) {
            kospiTemperatureMode =
                    false;

            return new KospiTemperatureStepResult(
                    true,
                    kospiTemperatureAverageController.getCurrentDay(),
                    kospiTemperatureAverageController.getTotalDay(),
                    run6Service.getClass6(),
                    kospiTemperatureAverageController.getProgressData()
            );
        }

        Map<String, Double> averageReturnMap =
                kospiTemperatureAverageController.nextDay();

        run6Service.resetDummy();

        Class6 class6Result =
                run6Service.createClass6ByAverageMap(
                        averageReturnMap
                );

        boolean finished =
                kospiTemperatureAverageController.isFinished();

        if (finished) {
            kospiTemperatureMode =
                    false;
        }

        return new KospiTemperatureStepResult(
                finished,
                kospiTemperatureAverageController.getCurrentDay(),
                kospiTemperatureAverageController.getTotalDay(),
                class6Result,
                kospiTemperatureAverageController.getProgressData()
        );
    }

    public void stopKospiTemperatureMode() {
        kospiTemperatureMode =
                false;
    }

    public boolean isKospiTemperatureMode() {
        return kospiTemperatureMode;
    }

    public record KospiTemperatureStepResult(
            boolean finished,
            int currentDay,
            int totalDay,
            Class6 class6Result,
            AnalysisProgressData progressData
    ) {
    }

    public RecommendRefreshResult createRecommendRefreshResult(
            Class6 class6Result
    ) {
        if (class6Result == null) {
            throw new IllegalArgumentException(
                    "Class6 결과는 null일 수 없습니다."
            );
        }

        if (lastHub3Items == null || lastHub3Items.isEmpty()) {
            throw new IllegalStateException(
                    "저장된 hub3Items가 없습니다. 일반 실행 흐름이 먼저 완료되어야 합니다."
            );
        }

        ViewClass6 viewClass6 =
                viewRun6.createViewClass6(
                        class6Result
                );

        return recommendRefreshService.createRecommendRefreshResult(
                viewClass6,
                lastHub3Items
        );
    }

    private themeMom createThemeByWeather(
            weatherMom weather
    ) {
        if (weather instanceof RainNow) {
            return new DeliveryTheme();
        }

        if (weather instanceof SunnyNow) {
            return new TravelTheme();
        }

        if (weather instanceof HeatWaveNow) {
            return new CoolingTheme();
        }

        if (weather instanceof ColdWaveNow) {
            return new HeatingTheme();
        }

        return new AirCareTheme();
    }

    private List<Stock> createAllStocks() {

        List<Stock> stocks =
                StockFileReader.loadAllStocks();

        if (stocks.size() != 25) {
            throw new IllegalStateException(
                    "전체 종목 수가 25개가 아닙니다. 현재 개수: "
                            + stocks.size()
            );
        }

        return stocks;
    }

    private double calculateRealtimeReturnRate(
            double currentPrice,
            List<Double> closePrices
    ) {
        if (currentPrice <= 0) {
            return 0.0;
        }

        if (closePrices == null || closePrices.size() < 2) {
            return 0.0;
        }

        double previousClose =
                closePrices.get(
                        closePrices.size() - 2
                );

        if (previousClose <= 0) {
            return 0.0;
        }

        return ((currentPrice - previousClose)
                / previousClose) * 100.0;
    }

    private StockPreparedResult createStockPreparedResult(
            List<Stock> stocks
    ) {
        if (stocks == null || stocks.isEmpty()) {
            throw new IllegalArgumentException(
                    "주식 목록은 비어 있을 수 없습니다."
            );
        }

        Realtime realtime =
                new Realtime();

        Parsing parsing =
                new Parsing();

        List<Hub3Data.Item> hub3Items =
                new ArrayList<>();

        List<CloseInfo> closeInfos =
                new ArrayList<>();

        for (Stock stock : stocks) {
            try {
                String realtimeJson =
                        realtime.getStockJson(
                                stock
                        );

                String closeJson =
                        parsing.getStockJson(
                                stock
                        );

                double currentPrice =
                        realtime.getCurrentPrice(
                                realtimeJson
                        );

                List<Double> closePrices =
                        realtime.getClosePrices(
                                closeJson
                        );

                double returnRate =
                        calculateRealtimeReturnRate(
                                currentPrice,
                                closePrices
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

                Thread.sleep(
                        300
                );

            } catch (Exception e) {
                System.out.println(
                        stock.getName()
                                + " 주식 정보 생성 실패"
                );
            }
        }

        if (hub3Items.isEmpty()) {
            throw new IllegalStateException(
                    "Hub3Data.Item 생성 결과가 비어 있습니다."
            );
        }

        if (closeInfos.isEmpty()) {
            throw new IllegalStateException(
                    "CloseInfo 생성 결과가 비어 있습니다."
            );
        }

        return new StockPreparedResult(
                hub3Items,
                closeInfos
        );
    }

    private record StockPreparedResult(
            List<Hub3Data.Item> hub3Items,
            List<CloseInfo> closeInfos
    ) {

        private StockPreparedResult {
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