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

    private final AppController appController;
    private final DummyStepListener dummyStepListener;

    private final Map<String, Integer> weatherCounts =
            new LinkedHashMap<>();

    private final JLabel progressLabel =
            new JLabel("진행: 0 / 365");

    private final JTextArea countArea =
            new JTextArea();

    private final JButton modeButton =
            new JButton("시작");

    private final JButton regionButton =
            new JButton("지역: 대구");

    private final JButton bonusButton =
            new JButton("보정: 0");

    private final Timer timer;

    private boolean running =
            false;

    private String regionName =
            "대구";

    private String filePath =
            "data/daegu_weather.csv";

    private int bonusLevel =
            0;

    public DummyModePanel(
            AppController appController,
            DummyStepListener dummyStepListener
    ) {
        this.appController =
                appController;

        this.dummyStepListener =
                dummyStepListener;

        this.timer =
                new Timer(
                        500,
                        e -> runOneDay()
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
                        new GridLayout(1, 3, 4, 4)
                );

        buttonPanel.add(
                modeButton
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

        modeButton.addActionListener(
                e -> toggleDummyMode()
        );

        regionButton.addActionListener(
                e -> changeRegion()
        );

        bonusButton.addActionListener(
                e -> changeBonusLevel()
        );
    }

    private void toggleDummyMode() {
        if (running) {
            stopDummyMode();
            return;
        }

        startDummyMode();
    }

    private void startDummyMode() {
        running =
                true;

        resetWeatherCounts();

        appController.startDummyMode(
                filePath
        );

        progressLabel.setText(
                "진행: 0 / 365"
        );

        modeButton.setText(
                "정지"
        );

        timer.start();
    }

    private void stopDummyMode() {
        running =
                false;

        timer.stop();

        appController.stopDummyMode();

        modeButton.setText(
                "시작"
        );
    }

    private void changeRegion() {
        if (running) {
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
        bonusLevel++;

        if (bonusLevel > 3) {
            bonusLevel =
                    0;
        }

        bonusButton.setText(
                "보정: " + bonusLevel
        );
    }

    private void runOneDay() {
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
            running =
                    false;

            timer.stop();

            appController.stopDummyMode();

            modeButton.setText(
                    "시작"
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