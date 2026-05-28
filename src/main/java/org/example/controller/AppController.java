package org.example.controller;

import org.example.view.MainFrame;
import org.example.model.*;
import org.example.service.*;
import org.example.util.StockFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppController {

    /*
     * =========================================================
     * [더미모드 연결 1]
     * DummyModeController는 CSV → Judge2 → Converter → Generator까지만 담당한다.
     * Class5/Class6 생성은 AppController가 run5/run6로 처리한다.
     * =========================================================
     */
    private final DummyModeController dummyModeController =
            new DummyModeController();

    /*
     * =========================================================
     * [KOSPI 평균 모드 연결 1]
     * KospiWeatherAverageController는
     * KOSPI-날씨 CSV → Judge2 → Converter → 테마별 KOSPI 평균수익률 계산까지만 담당한다.
     *
     * 여기서는 Class5를 쓰지 않고, Class6 그래프만 재사용한다.
     * =========================================================
     */
    private final KospiWeatherAverageController kospiWeatherAverageController =
            new KospiWeatherAverageController();

    /*
     * =========================================================
     * [KOSPI 기온 모드 연결 1]
     * KospiTemperatureAverageController는
     * KOSPI-날씨 CSV → 기온 구간 분류 → 구간별 KOSPI 평균수익률 계산까지만 담당한다.
     *
     * 여기서는 Class5를 쓰지 않고, Class6 그래프만 재사용한다.
     * =========================================================
     */
    private final KospiTemperatureAverageController kospiTemperatureAverageController =
            new KospiTemperatureAverageController();

    /*
     * =========================================================
     * [더미모드 연결 2]
     * run5/run6는 일반 경로와 더미 경로 둘 다 처리한다.
     * 같은 객체를 필드로 둬야 더미모드에서 7일 큐/누적값이 유지된다.
     * =========================================================
     */
    private final run5 run5Service =
            new run5();

    private final run6 run6Service =
            new run6();

    /*
     * =========================================================
     * [더미모드 연결 3]
     * View 버튼이 더미모드 시작/정지를 제어할 때 쓰는 상태값
     * =========================================================
     */
    private boolean dummyMode =
            false;

    /*
     * =========================================================
     * [KOSPI 평균 모드 연결 2]
     * View 버튼이 KOSPI 평균 모드 시작/정지를 제어할 때 쓰는 상태값
     * =========================================================
     */
    private boolean kospiAverageMode =
            false;

    /*
     * =========================================================
     * [KOSPI 기온 모드 연결 2]
     * View 버튼이 KOSPI 기온 모드 시작/정지를 제어할 때 쓰는 상태값
     * =========================================================
     */
    private boolean kospiTemperatureMode =
            false;

    public void run() {

        /*
         * 1. 날씨 1~2층
         */
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

        /*
         * 2. 현재 날씨 → 큰 테마 결정
         */
        themeMom theme =
                createThemeByWeather(
                        judgedWeather
                );

        /*
         * 3. stocks.csv에서 25개 종목 읽기
         */
        List<Stock> allStocks =
                createAllStocks();

        /*
         * 4. 실제 주식 데이터 생성
         * Hub3Data.Item = 3번/4번 현재가 흐름용
         * CloseInfo = 5번/6번 일반 경로용
         */
        StockPreparedResult stockPreparedResult =
                createStockPreparedResult(
                        allStocks
                );

        List<Hub3Data.Item> hub3Items =
                stockPreparedResult.hub3Items();

        List<CloseInfo> closeInfos =
                stockPreparedResult.closeInfos();

        /*
         * 5. 3층
         * themeMom + Hub3Data.Item 목록
         * → hub3 → Hub3Data → Judge3 → Delivery3 → Stem3
         */
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

        /*
         * 6. 3번 화면
         * Stem3 → run3 → Class3
         */
        run3 run3 =
                new run3();

        List<Class3> class3Result =
                run3.createClass3List(
                        stem3.sendToRun3()
                );

        /*
         * 7. 4번 대표 추천
         * Stem3 → Stem4 → Judge4 → Delivery4 → run4 → Class4
         */
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

        /*
         * 8. 5번/6번 일반 경로
         *
         * 일반 실행:
         * CloseInfo → run5Service → Class5
         * CloseInfo → run6Service → Class6
         *
         * 더미 실행:
         * DummyModeController가 만든 dailyThemeAverageReturns
         * → run5Service.createClass5ByDummy()
         * → run6Service.createClass6ByDummy()
         */
        Class5 class5Result =
                run5Service.createClass5ByCloseInfo(
                        closeInfos
                );

        Class6 class6Result =
                run6Service.createClass6ByCloseInfo(
                        closeInfos
                );

        /*
         * 9. 음식 추천
         */
        run9 run9Service =
                new run9();

        Class9 class9 =
                run9Service.createClass9(
                        judgedWeather.getName()
                );

        /*
         * 10. 콘솔 출력 확인
         */
        System.out.println("===== Class1 현재 날씨 =====");
        System.out.println(class1);

        System.out.println("===== 현재 테마 =====");
        System.out.println(theme.getThemeName());

        System.out.println("===== Class3 추천 종목 Top 5 =====");
        System.out.println(class3Result);

        System.out.println("===== Class4 대표 추천 =====");
        System.out.println(class4);

        System.out.println("===== Class5 최근 7일 테마 성과 =====");
        System.out.println(class5Result);

        System.out.println("===== Class6 테마별 누적수익률 =====");
        System.out.println(class6Result);

        System.out.println("===== Class9 음식 추천 =====");
        System.out.println(class9);

        new MainFrame(
                class1,
                class4,
                class3Result,
                class5Result,
                class6Result,
                class9,
                this
        );
    }

    /*
     * =========================================================
     * [더미모드 연결 4]
     * 8번 버튼이 처음 눌렸을 때 호출할 메서드
     *
     * 역할:
     * - 더미모드 ON
     * - CSV 365일 다시 처음부터 시작
     * - run5 더미 7일 큐 초기화
     * - run6 더미 누적수익률 초기화
     * =========================================================
     */
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

    /*
     * =========================================================
     * [더미모드 연결 5]
     * View의 Timer가 0.5초마다 호출할 메서드
     *
     * bonusLevel:
     * 0 → 보정 없음
     * 1 → 적중 테마 0~1% 보정
     * 2 → 적중 테마 0~2% 보정
     * 3 → 적중 테마 0~3% 보정
     *
     * 흐름:
     * DummyModeController
     * → dailyThemeAverageReturns 생성
     * → AppController가 run5/run6에 전달
     * → Class5/Class6 결과 반환
     * =========================================================
     */
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

    /*
     * =========================================================
     * [더미모드 연결 6]
     * View의 정지 버튼 또는 365일 완료 시 호출할 수 있는 메서드
     * =========================================================
     */
    public void stopDummyMode() {
        dummyMode =
                false;
    }

    /*
     * =========================================================
     * [더미모드 연결 7]
     * View가 현재 더미모드 실행 중인지 확인할 때 사용
     * =========================================================
     */
    public boolean isDummyMode() {
        return dummyMode;
    }

    /*
     * =========================================================
     * [더미모드 연결 8]
     * View가 365일 완료 여부 확인할 때 사용
     * =========================================================
     */
    public boolean isDummyModeFinished() {
        return dummyModeController.isFinished();
    }

    /*
     * =========================================================
     * [더미모드 연결 9]
     * View가 Timer 한 번 실행 후 받을 결과 묶음
     *
     * View는 여기서:
     * - currentDay / totalDay로 날짜 카운터 표시
     * - weatherName / themeName으로 8번 상태 표시
     * - class5Result로 5번 갱신
     * - class6Result로 6번 갱신
     * =========================================================
     */
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

    /*
     * =========================================================
     * [KOSPI 평균 모드 연결 3]
     * KOSPI 평균 모드 시작 메서드
     *
     * 역할:
     * - KOSPI 평균 모드 ON
     * - data/kospi_weather_2025.csv 처음부터 시작
     * - run6 더미 상태 초기화
     *
     * 이 모드는 Class5를 사용하지 않고 Class6 그래프만 갱신한다.
     * =========================================================
     */
    public void startKospiAverageMode() {
        kospiAverageMode =
                true;

        kospiWeatherAverageController.start(
                "data/kospi_weather_2025.csv"
        );

        run6Service.resetDummy();
    }

    /*
     * =========================================================
     * [KOSPI 평균 모드 연결 4]
     * View의 Timer가 0.5초마다 호출할 메서드
     *
     * 흐름:
     * KospiWeatherAverageController
     * → 하루치 KOSPI 수익률을 해당 날씨 테마에 반영
     * → 테마별 평균 KOSPI 수익률 Map 반환
     * → AppController가 run6에 전달
     * → Class6 결과 반환
     *
     * 주의:
     * run6는 원래 누적용이므로, 여기서는 매번 reset 후 평균 Map을 한 번만 넣어
     * Class6 그래프를 "평균수익률 표시용"으로 재사용한다.
     * =========================================================
     */
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
                    run6Service.getClass6()
            );
        }

        Map<String, Double> averageReturnMap =
                kospiWeatherAverageController.nextDay();

        /*
         * run6Service는 누적 구조이므로,
         * KOSPI 평균 모드에서는 매 tick마다 reset 후 평균값 Map을 한 번만 넣는다.
         */
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
                class6Result
        );
    }

    /*
     * =========================================================
     * [KOSPI 평균 모드 연결 5]
     * View의 정지 버튼 또는 완료 시 호출할 수 있는 메서드
     * =========================================================
     */
    public void stopKospiAverageMode() {
        kospiAverageMode =
                false;
    }

    /*
     * =========================================================
     * [KOSPI 평균 모드 연결 6]
     * View가 현재 KOSPI 평균 모드 실행 중인지 확인할 때 사용
     * =========================================================
     */
    public boolean isKospiAverageMode() {
        return kospiAverageMode;
    }

    /*
     * =========================================================
     * [KOSPI 평균 모드 연결 7]
     * View가 Timer 한 번 실행 후 받을 결과 묶음
     *
     * View는 여기서:
     * - currentDay / totalDay로 날짜 카운터 표시
     * - class6Result로 6번 그래프 갱신
     * =========================================================
     */
    public record KospiAverageStepResult(
            boolean finished,
            int currentDay,
            int totalDay,
            Class6 class6Result
    ) {
    }

    /*
     * =========================================================
     * [KOSPI 기온 모드 연결 3]
     * KOSPI 기온 분석 모드 시작 메서드
     *
     * 역할:
     * - KOSPI 기온 모드 ON
     * - data/kospi_weather_2025.csv 처음부터 시작
     * - run6 더미 상태 초기화
     *
     * 이 모드는 Class5를 사용하지 않고 Class6 그래프만 갱신한다.
     * =========================================================
     */
    public void startKospiTemperatureMode() {
        kospiTemperatureMode =
                true;

        kospiTemperatureAverageController.start(
                "data/kospi_weather_2025.csv"
        );

        run6Service.resetDummy();
    }

    /*
     * =========================================================
     * [KOSPI 기온 모드 연결 4]
     * View의 Timer가 0.5초마다 호출할 메서드
     *
     * 흐름:
     * KospiTemperatureAverageController
     * → 하루치 KOSPI 수익률을 해당 기온 구간에 반영
     * → 기온 구간별 평균 KOSPI 수익률 Map 반환
     * → AppController가 run6에 전달
     * → Class6 결과 반환
     *
     * 주의:
     * run6는 원래 누적용이므로, 여기서는 매번 reset 후 평균 Map을 한 번만 넣어
     * Class6 그래프를 "기온 구간별 평균수익률 표시용"으로 재사용한다.
     * =========================================================
     */
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
                    run6Service.getClass6()
            );
        }

        Map<String, Double> averageReturnMap =
                kospiTemperatureAverageController.nextDay();

        /*
         * run6Service는 누적 구조이므로,
         * KOSPI 기온 모드에서는 매 tick마다 reset 후 평균값 Map을 한 번만 넣는다.
         */
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
                class6Result
        );
    }

    /*
     * =========================================================
     * [KOSPI 기온 모드 연결 5]
     * View의 정지 버튼 또는 완료 시 호출할 수 있는 메서드
     * =========================================================
     */
    public void stopKospiTemperatureMode() {
        kospiTemperatureMode =
                false;
    }

    /*
     * =========================================================
     * [KOSPI 기온 모드 연결 6]
     * View가 현재 KOSPI 기온 모드 실행 중인지 확인할 때 사용
     * =========================================================
     */
    public boolean isKospiTemperatureMode() {
        return kospiTemperatureMode;
    }

    /*
     * =========================================================
     * [KOSPI 기온 모드 연결 7]
     * View가 Timer 한 번 실행 후 받을 결과 묶음
     *
     * View는 여기서:
     * - currentDay / totalDay로 날짜 카운터 표시
     * - class6Result로 6번 그래프 갱신
     * =========================================================
     */
    public record KospiTemperatureStepResult(
            boolean finished,
            int currentDay,
            int totalDay,
            Class6 class6Result
    ) {
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

        List<Hub3Data.Item> hub3Items =
                new ArrayList<>();

        List<CloseInfo> closeInfos =
                new ArrayList<>();

        for (Stock stock : stocks) {
            try {
                String json =
                        realtime.getStockJson(
                                stock
                        );

                double currentPrice =
                        realtime.getCurrentPrice(
                                json
                        );

                List<Double> closePrices =
                        realtime.getClosePrices(
                                json
                        );

                double returnRate =
                        realtime.getReturnRate(
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