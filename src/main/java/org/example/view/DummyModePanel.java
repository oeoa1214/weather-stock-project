package org.example.view;

import org.example.controller.AppController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

public class DummyModePanel extends JPanel {

    public interface DummyStepListener {
        void onStep(
                AppController.DummyStepResult result
        );
    }

    public interface KospiStepListener {
        void onStep(
                AppController.KospiAverageStepResult result
        );
    }

    public interface TemperatureStepListener {
        void onStep(
                AppController.KospiTemperatureStepResult result
        );
    }

    private static final String MODE_NONE =
            "NONE";

    private static final String MODE_DUMMY =
            "DUMMY";

    private static final String MODE_KOSPI =
            "KOSPI";

    private static final String MODE_TEMPERATURE =
            "TEMPERATURE";

    private final AppController appController;
    private final DummyStepListener dummyStepListener;
    private final KospiStepListener kospiStepListener;
    private final TemperatureStepListener temperatureStepListener;

    private final Map<String, Integer> weatherCounts =
            new LinkedHashMap<>();

    private final JLabel progressLabel =
            new JLabel("진행: 0 / 365");

    private final JTextArea countArea =
            new JTextArea();

    private final JButton dummyButton =
            new JButton("더미 시작");

    private final JButton kospiButton =
            new JButton("KOSPI 분석");

    private final JButton temperatureButton =
            new JButton("기온 분석");

    private final JButton regionButton =
            new JButton("지역: 대구");

    private final JButton bonusButton =
            new JButton("보정: 0");

    private final Timer timer;

    private String runningMode =
            MODE_NONE;

    private String regionName =
            "대구";

    private String filePath =
            "data/daegu_weather.csv";

    private int bonusLevel =
            0;

    public DummyModePanel(
            AppController appController,
            DummyStepListener dummyStepListener,
            KospiStepListener kospiStepListener,
            TemperatureStepListener temperatureStepListener
    ) {
        this.appController =
                appController;

        this.dummyStepListener =
                dummyStepListener;

        this.kospiStepListener =
                kospiStepListener;

        this.temperatureStepListener =
                temperatureStepListener;

        this.timer =
                new Timer(
                        500,
                        e -> runOneStep()
                );

        setLayout(
                new BorderLayout(4, 4)
        );

        resetWeatherCounts();

        progressLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 12)
        );

        countArea.setEditable(
                false
        );

        countArea.setFont(
                new Font("맑은 고딕", Font.PLAIN, 12)
        );

        countArea.setOpaque(
                false
        );

        JPanel topPanel =
                new JPanel(
                        new GridLayout(1, 1)
                );

        topPanel.add(
                progressLabel
        );

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(3, 2, 4, 4)
                );

        buttonPanel.add(
                dummyButton
        );

        buttonPanel.add(
                kospiButton
        );

        buttonPanel.add(
                temperatureButton
        );

        buttonPanel.add(
                regionButton
        );

        buttonPanel.add(
                bonusButton
        );

        add(
                topPanel,
                BorderLayout.NORTH
        );

        add(
                countArea,
                BorderLayout.CENTER
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        dummyButton.addActionListener(
                e -> toggleDummyMode()
        );

        kospiButton.addActionListener(
                e -> toggleKospiMode()
        );

        temperatureButton.addActionListener(
                e -> toggleTemperatureMode()
        );

        regionButton.addActionListener(
                e -> changeRegion()
        );

        bonusButton.addActionListener(
                e -> changeBonusLevel()
        );
    }

    private void toggleDummyMode() {
        if (MODE_DUMMY.equals(runningMode)) {
            stopCurrentMode();
            return;
        }

        if (!MODE_NONE.equals(runningMode)) {
            stopCurrentMode();
        }

        startDummyMode();
    }

    private void toggleKospiMode() {
        if (MODE_KOSPI.equals(runningMode)) {
            stopCurrentMode();
            return;
        }

        if (!MODE_NONE.equals(runningMode)) {
            stopCurrentMode();
        }

        startKospiMode();
    }

    private void toggleTemperatureMode() {
        if (MODE_TEMPERATURE.equals(runningMode)) {
            stopCurrentMode();
            return;
        }

        if (!MODE_NONE.equals(runningMode)) {
            stopCurrentMode();
        }

        startTemperatureMode();
    }

    private void startDummyMode() {
        runningMode =
                MODE_DUMMY;

        resetWeatherCounts();

        appController.startDummyMode(
                filePath
        );

        progressLabel.setText(
                "진행: 0 / 365"
        );

        dummyButton.setText(
                "더미 정지"
        );

        kospiButton.setText(
                "KOSPI 분석"
        );

        temperatureButton.setText(
                "기온 분석"
        );

        timer.start();
    }

    private void startKospiMode() {
        runningMode =
                MODE_KOSPI;

        appController.startKospiAverageMode();

        progressLabel.setText(
                "KOSPI: 0 / 0"
        );

        countArea.setText(
                "실제 KOSPI 수익률을\n날씨 테마별 평균으로 계산 중입니다."
        );

        dummyButton.setText(
                "더미 시작"
        );

        kospiButton.setText(
                "KOSPI 정지"
        );

        temperatureButton.setText(
                "기온 분석"
        );

        timer.start();
    }

    private void startTemperatureMode() {
        runningMode =
                MODE_TEMPERATURE;

        appController.startKospiTemperatureMode();

        progressLabel.setText(
                "기온: 0 / 0"
        );

        countArea.setText(
                "실제 KOSPI 수익률을\n기온 구간별 평균으로 계산 중입니다."
        );

        dummyButton.setText(
                "더미 시작"
        );

        kospiButton.setText(
                "KOSPI 분석"
        );

        temperatureButton.setText(
                "기온 정지"
        );

        timer.start();
    }

    private void stopCurrentMode() {
        timer.stop();

        if (MODE_DUMMY.equals(runningMode)) {
            appController.stopDummyMode();
        }

        if (MODE_KOSPI.equals(runningMode)) {
            appController.stopKospiAverageMode();
        }

        if (MODE_TEMPERATURE.equals(runningMode)) {
            appController.stopKospiTemperatureMode();
        }

        runningMode =
                MODE_NONE;

        dummyButton.setText(
                "더미 시작"
        );

        kospiButton.setText(
                "KOSPI 분석"
        );

        temperatureButton.setText(
                "기온 분석"
        );
    }

    private void changeRegion() {
        if (!MODE_NONE.equals(runningMode)) {
            return;
        }

        if (regionName.equals("대구")) {
            regionName =
                    "서울";

            filePath =
                    "data/seoul_weather.csv";
        } else {
            regionName =
                    "대구";

            filePath =
                    "data/daegu_weather.csv";
        }

        regionButton.setText(
                "지역: " + regionName
        );
    }

    private void changeBonusLevel() {
        if (!MODE_NONE.equals(runningMode)) {
            return;
        }

        bonusLevel++;

        if (bonusLevel > 3) {
            bonusLevel =
                    0;
        }

        bonusButton.setText(
                "보정: " + bonusLevel
        );
    }

    private void runOneStep() {
        if (MODE_DUMMY.equals(runningMode)) {
            runDummyOneDay();
            return;
        }

        if (MODE_KOSPI.equals(runningMode)) {
            runKospiOneDay();
            return;
        }

        if (MODE_TEMPERATURE.equals(runningMode)) {
            runTemperatureOneDay();
        }
    }

    private void runDummyOneDay() {
        AppController.DummyStepResult result =
                appController.runDummyOneDay(
                        bonusLevel
                );

        progressLabel.setText(
                "진행: "
                        + result.currentDay()
                        + " / "
                        + result.totalDay()
        );

        if (!result.weatherName().equals("완료")) {
            countWeather(
                    result.weatherName()
            );
        }

        countArea.setText(
                createCountText()
        );

        dummyStepListener.onStep(
                result
        );

        if (result.finished()) {
            stopCurrentMode();

            progressLabel.setText(
                    "진행: "
                            + result.currentDay()
                            + " / "
                            + result.totalDay()
            );
        }
    }

    private void runKospiOneDay() {
        AppController.KospiAverageStepResult result =
                appController.runKospiAverageOneDay();

        progressLabel.setText(
                "KOSPI: "
                        + result.currentDay()
                        + " / "
                        + result.totalDay()
        );

        countArea.setText(
                "실제 KOSPI 수익률을\n날씨 테마별 평균으로 계산 중입니다."
        );

        kospiStepListener.onStep(
                result
        );

        if (result.finished()) {
            stopCurrentMode();

            progressLabel.setText(
                    "KOSPI: "
                            + result.currentDay()
                            + " / "
                            + result.totalDay()
            );
        }
    }

    private void runTemperatureOneDay() {
        AppController.KospiTemperatureStepResult result =
                appController.runKospiTemperatureOneDay();

        progressLabel.setText(
                "기온: "
                        + result.currentDay()
                        + " / "
                        + result.totalDay()
        );

        countArea.setText(
                "실제 KOSPI 수익률을\n기온 구간별 평균으로 계산 중입니다."
        );

        temperatureStepListener.onStep(
                result
        );

        if (result.finished()) {
            stopCurrentMode();

            progressLabel.setText(
                    "기온: "
                            + result.currentDay()
                            + " / "
                            + result.totalDay()
            );
        }
    }

    private void resetWeatherCounts() {
        weatherCounts.clear();

        weatherCounts.put("맑음", 0);
        weatherCounts.put("비", 0);
        weatherCounts.put("폭염", 0);
        weatherCounts.put("한파", 0);
        weatherCounts.put("미세먼지", 0);

        countArea.setText(
                createCountText()
        );
    }

    private void countWeather(
            String weatherName
    ) {
        if (!weatherCounts.containsKey(weatherName)) {
            return;
        }

        weatherCounts.put(
                weatherName,
                weatherCounts.get(weatherName) + 1
        );
    }

    private String createCountText() {
        StringBuilder builder =
                new StringBuilder();

        for (Map.Entry<String, Integer> entry : weatherCounts.entrySet()) {
            builder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("회\n");
        }

        return builder.toString();
    }
}