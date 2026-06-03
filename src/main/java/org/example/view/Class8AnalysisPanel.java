package org.example.view;

import org.example.controller.AppController;
import org.example.model.AnalysisProgressData;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.LinkedHashMap;
import java.util.Map;

public class Class8AnalysisPanel extends JPanel {

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

    private static final Color LAB_BACKGROUND =
            new Color(245, 250, 255);

    private static final Color PANEL_BACKGROUND =
            new Color(235, 246, 255);

    private static final Color BORDER_BLUE =
            new Color(120, 190, 235);

    private static final Color POINT_BLUE =
            new Color(65, 150, 220);

    private static final Color TEXT_DARK =
            new Color(35, 55, 75);

    private static final Color TEXT_SUB =
            new Color(70, 95, 120);

    private static final Color BUTTON_BACKGROUND =
            new Color(225, 242, 255);

    private static final Color BUTTON_BORDER =
            new Color(100, 175, 230);

    private final AppController appController;
    private final DummyStepListener dummyStepListener;
    private final KospiStepListener kospiStepListener;
    private final TemperatureStepListener temperatureStepListener;

    private final Class8ButtonController buttonController;

    private final Map<String, Integer> weatherCounts =
            new LinkedHashMap<>();

    private final JLabel progressLabel =
            new JLabel("LAB READY");

    private final JTextArea countArea =
            new JTextArea();

    private final JButton dummyButton =
            new JButton("더미 실험");

    private final JButton kospiButton =
            new JButton("KOSPI 분석");

    private final JButton temperatureButton =
            new JButton("기온 분석");

    private final JButton regionButton =
            new JButton("지역: 대구");

    private final JButton defaultButton =
            new JButton("기본 모드");

    private final Timer timer;

    private String runningMode =
            MODE_NONE;

    private String regionName =
            "대구";

    private String filePath =
            "data/daegu_weather.csv";

    public Class8AnalysisPanel(
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

        this.buttonController =
                new Class8ButtonControllerImpl(
                        this
                );

        this.timer =
                new Timer(
                        100,
                        e -> runOneStep()
                );

        setLayout(
                new BorderLayout(6, 6)
        );

        setBackground(
                LAB_BACKGROUND
        );

        setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_BLUE,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                6,
                                6,
                                6,
                                6
                        )
                )
        );

        resetWeatherCounts();

        progressLabel.setFont(
                new Font("Bahnschrift", Font.BOLD, 13)
        );

        progressLabel.setForeground(
                POINT_BLUE
        );

        progressLabel.setOpaque(
                true
        );

        progressLabel.setBackground(
                PANEL_BACKGROUND
        );

        progressLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        3,
                        6,
                        3,
                        6
                )
        );

        countArea.setEditable(
                false
        );

        countArea.setFont(
                new Font("경기천년제목", Font.BOLD, 12)
        );

        countArea.setForeground(
                TEXT_DARK
        );

        countArea.setBackground(
                Color.WHITE
        );

        countArea.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(210, 230, 245),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                7,
                                5,
                                7
                        )
                )
        );

        JPanel topPanel =
                new JPanel(
                        new GridLayout(1, 1)
                );

        topPanel.setOpaque(
                false
        );

        topPanel.add(
                progressLabel
        );

        JPanel buttonPanel =
                createButtonPanel();

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
                e -> buttonController.clickDummyButton()
        );

        kospiButton.addActionListener(
                e -> buttonController.clickKospiButton()
        );

        temperatureButton.addActionListener(
                e -> buttonController.clickTemperatureButton()
        );

        regionButton.addActionListener(
                e -> buttonController.clickRegionButton()
        );

        defaultButton.addActionListener(
                e -> buttonController.clickDefaultButton()
        );
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(2, 1, 4, 4)
                );

        buttonPanel.setOpaque(
                false
        );

        JPanel modeButtonPanel =
                new JPanel(
                        new GridLayout(1, 3, 4, 4)
                );

        modeButtonPanel.setOpaque(
                false
        );

        setupLabButton(
                dummyButton
        );

        setupLabButton(
                kospiButton
        );

        setupLabButton(
                temperatureButton
        );

        modeButtonPanel.add(
                dummyButton
        );

        modeButtonPanel.add(
                kospiButton
        );

        modeButtonPanel.add(
                temperatureButton
        );

        JPanel optionButtonPanel =
                new JPanel(
                        new GridLayout(1, 2, 4, 4)
                );

        optionButtonPanel.setOpaque(
                false
        );

        setupLabButton(
                regionButton
        );

        setupLabButton(
                defaultButton
        );

        optionButtonPanel.add(
                regionButton
        );

        optionButtonPanel.add(
                defaultButton
        );

        buttonPanel.add(
                modeButtonPanel
        );

        buttonPanel.add(
                optionButtonPanel
        );

        return buttonPanel;
    }

    private void setupLabButton(
            JButton button
    ) {
        button.setFont(
                new Font("경기천년제목", Font.BOLD, 11)
        );

        button.setFocusPainted(
                false
        );

        button.setBackground(
                BUTTON_BACKGROUND
        );

        button.setForeground(
                TEXT_SUB
        );

        button.setBorder(
                new LineBorder(
                        BUTTON_BORDER,
                        1
                )
        );
    }

    void handleDummyButton() {
        if (MODE_DUMMY.equals(runningMode)) {
            stopCurrentMode();
            return;
        }

        if (!MODE_NONE.equals(runningMode)) {
            stopCurrentMode();
        }

        startDummyMode();
    }

    void handleKospiButton() {
        if (MODE_KOSPI.equals(runningMode)) {
            stopCurrentMode();
            return;
        }

        if (!MODE_NONE.equals(runningMode)) {
            stopCurrentMode();
        }

        startKospiMode();
    }

    void handleTemperatureButton() {
        if (MODE_TEMPERATURE.equals(runningMode)) {
            stopCurrentMode();
            return;
        }

        if (!MODE_NONE.equals(runningMode)) {
            stopCurrentMode();
        }

        startTemperatureMode();
    }

    void handleRegionButton() {
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

        countArea.setText(
                "지역이 "
                        + regionName
                        + "로 변경되었습니다.\n"
                        + "분석 모드를 선택하세요."
        );
    }

    void handleDefaultButton() {
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

        Window window =
                SwingUtilities.getWindowAncestor(
                        this
                );

        if (window != null) {
            window.dispose();
        }

        SwingUtilities.invokeLater(
                () -> new AppController().run()
        );
    }

    private void startDummyMode() {
        runningMode =
                MODE_DUMMY;

        resetWeatherCounts();

        appController.startDummyMode(
                filePath
        );

        progressLabel.setText(
                "DUMMY LAB · 0 / 365"
        );

        countArea.setText(
                "더미 날씨 실험을 시작합니다.\n"
                        + "지역: "
                        + regionName
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

        appController.startKospiAverageMode(
                getKospiFilePath()
        );

        progressLabel.setText(
                "KOSPI LAB · 0 / 0"
        );

        countArea.setText(
                "KOSPI WEATHER COUNT\n"
                        + "지역: "
                        + regionName
                        + "\n"
                        + "데이터를 불러오는 중입니다."
        );

        dummyButton.setText(
                "더미 실험"
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

        appController.startKospiTemperatureMode(
                getKospiFilePath()
        );

        progressLabel.setText(
                "TEMP LAB · 0 / 0"
        );

        countArea.setText(
                "TEMPERATURE COUNT\n"
                        + "지역: "
                        + regionName
                        + "\n"
                        + "데이터를 불러오는 중입니다."
        );

        dummyButton.setText(
                "더미 실험"
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
                "더미 실험"
        );

        kospiButton.setText(
                "KOSPI 분석"
        );

        temperatureButton.setText(
                "기온 분석"
        );

        progressLabel.setText(
                "LAB READY"
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
                        0
                );

        progressLabel.setText(
                "DUMMY LAB · "
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
                createDummyCountText()
        );

        dummyStepListener.onStep(
                result
        );

        if (result.finished()) {
            stopCurrentMode();

            progressLabel.setText(
                    "DUMMY COMPLETE · "
                            + result.currentDay()
                            + " / "
                            + result.totalDay()
            );
        }
    }

    private void runKospiOneDay() {
        AppController.KospiAverageStepResult result =
                appController.runKospiAverageOneDay();

        AnalysisProgressData progressData =
                result.progressData();

        progressLabel.setText(
                "KOSPI LAB · "
                        + result.currentDay()
                        + " / "
                        + result.totalDay()
        );

        countArea.setText(
                createAnalysisCountText(
                        "KOSPI WEATHER COUNT",
                        progressData
                )
        );

        kospiStepListener.onStep(
                result
        );

        if (result.finished()) {
            stopCurrentMode();

            progressLabel.setText(
                    "KOSPI COMPLETE · "
                            + result.currentDay()
                            + " / "
                            + result.totalDay()
            );

            countArea.setText(
                    createAnalysisCountText(
                            "KOSPI WEATHER COUNT",
                            progressData
                    )
            );
        }
    }

    private void runTemperatureOneDay() {
        AppController.KospiTemperatureStepResult result =
                appController.runKospiTemperatureOneDay();

        AnalysisProgressData progressData =
                result.progressData();

        progressLabel.setText(
                "TEMP LAB · "
                        + result.currentDay()
                        + " / "
                        + result.totalDay()
        );

        countArea.setText(
                createAnalysisCountText(
                        "TEMPERATURE COUNT",
                        progressData
                )
        );

        temperatureStepListener.onStep(
                result
        );

        if (result.finished()) {
            stopCurrentMode();

            progressLabel.setText(
                    "TEMP COMPLETE · "
                            + result.currentDay()
                            + " / "
                            + result.totalDay()
            );

            countArea.setText(
                    createAnalysisCountText(
                            "TEMPERATURE COUNT",
                            progressData
                    )
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
                createDummyCountText()
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

    private String createDummyCountText() {
        StringBuilder builder =
                new StringBuilder();

        builder.append("WEATHER COUNT\n");

        for (Map.Entry<String, Integer> entry : weatherCounts.entrySet()) {
            builder.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue())
                    .append("회\n");
        }

        return builder.toString();
    }

    private String createAnalysisCountText(
            String title,
            AnalysisProgressData progressData
    ) {
        if (progressData == null) {
            return title + "\n데이터가 없습니다.";
        }

        StringBuilder builder =
                new StringBuilder();

        builder.append(title)
                .append("\n");

        builder.append("진행 : ")
                .append(progressData.currentDay())
                .append(" / ")
                .append(progressData.totalDay())
                .append("일\n");

        builder.append("날짜 : ")
                .append(progressData.currentDate())
                .append("\n\n");

        Map<String, Integer> countMap =
                progressData.countMap();

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            builder.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue())
                    .append("회\n");
        }

        return builder.toString();
    }

    private String getKospiFilePath() {
        if (regionName.equals("대구")) {
            return "data/kospi_weather_2025.csv_dague";
        }

        return "data/kospi_weather_2025.csv";
    }
}