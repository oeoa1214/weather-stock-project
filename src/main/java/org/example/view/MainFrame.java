package org.example.view;

import org.example.controller.AppController;
import org.example.model.AnalysisProgressData;
import org.example.model.Class1;
import org.example.model.Class3;
import org.example.model.Class4;
import org.example.model.Class5;
import org.example.model.Class6;
import org.example.model.Class7;
import org.example.model.Class9;
import org.example.model.RecommendRefreshResult;
import org.example.service.Class7AppliedRateCalculator;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {

    private final AppController appController;
    private final Class1 class1;
    private final List<Class7> class7Result;

    private JPanel weatherCardPanel;
    private JPanel rootPanel;
    private JPanel gridPanel;
    private JLabel titleLabel;

    private final Class7AppliedRateCalculator class7AppliedRateCalculator =
            new Class7AppliedRateCalculator();

    private Color backgroundColor;
    private Color textColor;

    public MainFrame(
            Class1 class1,
            Class4 class4,
            List<Class3> class3Result,
            Class5 class5Result,
            Class6 class6Result,
            List<Class7> class7Result,
            Class9 class9,
            AppController appController
    ) {
        this.class1 =
                class1;

        this.class7Result =
                class7Result;

        this.appController =
                appController;

        applyColorFields(
                class1.condition().getName()
        );

        setTitle("날씨 기반 주식 테마 다 각도 의사결정 결정 지원 시스템");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rootPanel =
                new JPanel(
                        new BorderLayout(12, 12)
                );

        rootPanel.setBackground(
                backgroundColor
        );

        rootPanel.setBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        titleLabel =
                new JLabel(
                        "WeatherStock prismatic analytic  decision support system",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font("경기천년제목", Font.BOLD, 22)
        );

        titleLabel.setForeground(
                textColor
        );

        rootPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        gridPanel =
                new JPanel(
                        new GridLayout(3, 3, 10, 10)
                );

        gridPanel.setBackground(
                backgroundColor
        );

        weatherCardPanel =
                createWeatherCard(
                        class1.condition().getName(),
                        class1
                );

        gridPanel.add(
                weatherCardPanel
        );

        gridPanel.add(
                new ThemeRecommendPanel(
                        class4
                )
        );

        gridPanel.add(
                new CardPanel(
                        "3. 추천 종목 Top 5",
                        new Class3RankingPanel(
                                class3Result
                        )
                )
        );

        gridPanel.add(
                new WeatherThemeButtonPanel(
                        this::changeManualThemeColor,
                        this::getManualButtonSelectedColor
                )
        );

        gridPanel.add(
                new CardPanel(
                        "5. 테마별 최근 7일 성과",
                        new Class5ScoreboardPanel(
                                class5Result
                        )
                )
        );

        gridPanel.add(
                createClass6Card(
                        "6. 테마별 누적수익률",
                        class6Result
                )
        );

        gridPanel.add(
                new CardPanel(
                        "7. 테마 대표 종목 현황",
                        new Class7ThemeStockPanel(
                                class7Result
                        )
                )
        );

        gridPanel.add(
                new CardPanel(
                        "8. 지역별 날씨·KOSPI 분석 모드",
                        new Class8AnalysisPanel(
                                appController,
                                this::applyDummyStepResult,
                                this::applyKospiAverageStepResult,
                                this::applyKospiTemperatureStepResult
                        )
                )
        );

        gridPanel.add(
                new FoodCardPanel(
                        class9
                )
        );

        rootPanel.add(
                gridPanel,
                BorderLayout.CENTER
        );

        setContentPane(
                rootPanel
        );

        setVisible(
                true
        );
    }

    private void changeManualThemeColor(
            String weatherName
    ) {
        String colorWeatherName =
                weatherName;

        if (weatherName.equals("실시간")) {
            colorWeatherName =
                    class1.condition().getName();
        }

        applyColorFields(
                colorWeatherName
        );

        rootPanel.setBackground(
                backgroundColor
        );

        gridPanel.setBackground(
                backgroundColor
        );

        titleLabel.setForeground(
                textColor
        );

        JPanel newWeatherCardPanel =
                createWeatherCard(
                        colorWeatherName,
                        class1
                );

        gridPanel.remove(
                weatherCardPanel
        );

        weatherCardPanel =
                newWeatherCardPanel;

        gridPanel.add(
                weatherCardPanel,
                0
        );

        gridPanel.revalidate();
        gridPanel.repaint();

        rootPanel.revalidate();
        rootPanel.repaint();
    }

    private Color getManualButtonSelectedColor(
            String weatherName
    ) {
        String colorWeatherName =
                weatherName;

        if (weatherName.equals("실시간")) {
            colorWeatherName =
                    class1.condition().getName();
        }

        return getWeatherCardMainColor(
                colorWeatherName
        );
    }

    private JPanel createWeatherCard(
            String weatherName,
            Class1 class1
    ) {
        Color cardColor =
                getWeatherCardMainColor(
                        weatherName
                );

        JPanel panel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        panel.setBackground(
                cardColor
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                cardColor,
                                2
                        ),
                        BorderFactory.createEmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "1. 현재 날씨 정보",
                        SwingConstants.LEFT
                );

        title.setFont(
                new Font("경기천년제목", Font.BOLD, 13)
        );

        title.setForeground(
                Color.WHITE
        );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.setOpaque(
                false
        );

        JLabel temperatureLabel =
                new JLabel(
                        class1.temperature() + "℃",
                        SwingConstants.LEFT
                );

        temperatureLabel.setFont(
                new Font("경기천년제목", Font.BOLD, 42)
        );

        temperatureLabel.setForeground(
                Color.WHITE
        );

        JLabel weatherLabel =
                new JLabel(
                        weatherName + "  " + getWeatherEnglishName(
                                weatherName
                        ),
                        SwingConstants.LEFT
                );

        weatherLabel.setFont(
                new Font("경기천년제목", Font.BOLD, 18)
        );

        weatherLabel.setForeground(
                Color.WHITE
        );

        JPanel textPanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        textPanel.setOpaque(
                false
        );

        textPanel.add(
                temperatureLabel
        );

        textPanel.add(
                weatherLabel
        );

        JLabel iconLabel =
                new JLabel(
                        getWeatherMascot(
                                weatherName
                        ),
                        SwingConstants.CENTER
                );

        iconLabel.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 48)
        );

        iconLabel.setForeground(
                Color.WHITE
        );

        centerPanel.add(
                textPanel,
                BorderLayout.CENTER
        );

        panel.add(
                iconLabel,
                BorderLayout.EAST
        );

        JPanel detailPanel =
                new JPanel(
                        new GridLayout(2, 2, 4, 2)
                );

        detailPanel.setOpaque(
                false
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "강수량 " + class1.precipitation()
                )
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "풍속 " + class1.windspeed()
                )
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "미세먼지 " + class1.pm10()
                )
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "습도 " + class1.humidity() + "%"
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        panel.add(
                detailPanel,
                BorderLayout.SOUTH
        );

        return panel;
    }

    private JPanel createAnalysisWeatherCard(
            AnalysisProgressData progressData
    ) {
        String weatherName =
                progressData.colorWeatherName();

        Color cardColor =
                getWeatherCardMainColor(
                        weatherName
                );

        JPanel panel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        panel.setBackground(
                cardColor
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );

        JLabel title =
                new JLabel(
                        "1. 분석 날짜 날씨 정보",
                        SwingConstants.LEFT
                );

        title.setFont(
                new Font("경기천년제목", Font.BOLD, 13)
        );

        title.setForeground(
                Color.WHITE
        );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.setOpaque(
                false
        );

        JLabel temperatureLabel =
                new JLabel(
                        String.format(
                                "%.1f℃",
                                progressData.averageTemperature()
                        ),
                        SwingConstants.LEFT
                );

        temperatureLabel.setFont(
                new Font("경기천년제목", Font.BOLD, 42)
        );

        temperatureLabel.setForeground(
                Color.WHITE
        );

        JLabel weatherLabel =
                new JLabel(
                        weatherName + "  " + getWeatherEnglishName(
                                weatherName
                        ),
                        SwingConstants.LEFT
                );

        weatherLabel.setFont(
                new Font("경기천년제목", Font.BOLD, 18)
        );

        weatherLabel.setForeground(
                Color.WHITE
        );

        JPanel textPanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        textPanel.setOpaque(
                false
        );

        textPanel.add(
                temperatureLabel
        );

        textPanel.add(
                weatherLabel
        );

        JLabel iconLabel =
                new JLabel(
                        getWeatherMascot(
                                weatherName
                        ),
                        SwingConstants.CENTER
                );

        iconLabel.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 48)
        );

        iconLabel.setForeground(
                Color.WHITE
        );

        centerPanel.add(
                textPanel,
                BorderLayout.CENTER
        );

        panel.add(
                iconLabel,
                BorderLayout.EAST
        );

        JPanel detailPanel =
                new JPanel(
                        new GridLayout(2, 2, 4, 2)
                );

        detailPanel.setOpaque(
                false
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "날짜 " + progressData.currentDate()
                )
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "진행 "
                                + progressData.currentDay()
                                + "/"
                                + progressData.totalDay()
                )
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "강수량 " + progressData.precipitation()
                )
        );

        detailPanel.add(
                createWeatherDetailLabel(
                        "미세먼지 " + progressData.pm10()
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        panel.add(
                detailPanel,
                BorderLayout.SOUTH
        );

        return panel;
    }

    private JLabel createWeatherDetailLabel(
            String text
    ) {
        JLabel label =
                new JLabel(
                        text,
                        SwingConstants.LEFT
                );

        label.setFont(
                new Font("경기천년제목", Font.BOLD, 12)
        );

        label.setForeground(
                Color.WHITE
        );

        return label;
    }

    private JScrollPane createClass6Card(
            String title,
            Class6 class6Result
    ) {
        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                new Color(255, 252, 255)
        );

        TitledBorder titledBorder =
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 205, 220),
                                1
                        ),
                        title,
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("경기천년제목", Font.BOLD, 13),
                        new Color(80, 70, 90)
                );

        panel.setBorder(
                titledBorder
        );

        panel.add(
                new Class6GraphPanel(
                        class6Result
                ),
                BorderLayout.CENTER
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        panel
                );

        scrollPane.setBorder(
                null
        );

        return scrollPane;
    }

    private void replaceGridCard(
            int index,
            java.awt.Component component
    ) {
        gridPanel.remove(
                index
        );

        gridPanel.add(
                component,
                index
        );
    }

    private void applyAnalysisProgressVisual(
            AnalysisProgressData progressData
    ) {
        if (progressData == null) {
            return;
        }

        applyColorFields(
                progressData.colorWeatherName()
        );

        rootPanel.setBackground(
                backgroundColor
        );

        gridPanel.setBackground(
                backgroundColor
        );

        titleLabel.setForeground(
                textColor
        );

        JPanel newWeatherCardPanel =
                createAnalysisWeatherCard(
                        progressData
                );

        gridPanel.remove(
                weatherCardPanel
        );

        weatherCardPanel =
                newWeatherCardPanel;

        gridPanel.add(
                weatherCardPanel,
                0
        );
    }

    private void applyDummyStepResult(
            AppController.DummyStepResult result
    ) {
        if (result == null || result.weatherName().equals("완료")) {
            return;
        }

        changeManualThemeColor(
                result.weatherName()
        );

        replaceGridCard(
                5,
                createClass6Card(
                        "6. 테마별 누적수익률",
                        result.class6Result()
                )
        );

        replaceGridCard(
                4,
                new CardPanel(
                        "5. 테마별 최근 7일 성과",
                        new Class5ScoreboardPanel(
                                result.class5Result()
                        )
                )
        );

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void applyKospiAverageStepResult(
            AppController.KospiAverageStepResult result
    ) {
        if (result == null) {
            return;
        }

        applyAnalysisProgressVisual(
                result.progressData()
        );

        replaceGridCard(
                4,
                new CardPanel(
                        "5. 최근 7거래일 KOSPI 날씨 테마 누적 성과",
                        new Class5ScoreboardPanel(
                                result.class5Result()
                        )
                )
        );

        replaceGridCard(
                5,
                createClass6Card(
                        "6. 날씨별 KOSPI 일일 평균 수익률",
                        result.class6Result()
                )
        );

        Map<String, Double> appliedRateMap =
                class7AppliedRateCalculator.createAppliedRateMapByCount(
                        result.class6Result(),
                        result.progressData().appliedCountMap()
                );

        replaceGridCard(
                6,
                new CardPanel(
                        "7. KOSPI 누적 반영 테마 대표가",
                        new Class7ThemeStockPanel(
                                class7Result,
                                appliedRateMap,
                                "KOSPI 누적 반영 대표 종목"
                        )
                )
        );

        if (result.finished()) {
            RecommendRefreshResult refreshResult =
                    appController.createRecommendRefreshResult(
                            result.class6Result()
                    );

            applyRecommendRefreshResult(
                    refreshResult
            );
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void applyKospiTemperatureStepResult(
            AppController.KospiTemperatureStepResult result
    ) {
        if (result == null) {
            return;
        }

        applyAnalysisProgressVisual(
                result.progressData()
        );

        replaceGridCard(
                5,
                createClass6Card(
                        "6. 기온 구간별 KOSPI 하루 평균 수익률",
                        result.class6Result()
                )
        );

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void applyRecommendRefreshResult(
            RecommendRefreshResult result
    ) {
        if (result == null) {
            return;
        }

        replaceGridCard(
                1,
                new ThemeRecommendPanel(
                        result.class4()
                )
        );

        replaceGridCard(
                2,
                new CardPanel(
                        "3. 추천 종목 Top 5",
                        new Class3RankingPanel(
                                result.class3Result()
                        )
                )
        );

        replaceGridCard(
                8,
                new FoodCardPanel(
                        result.class9()
                )
        );
    }

    private void applyColorFields(
            String weatherName
    ) {
        WeatherColor weatherColor =
                getWeatherColor(
                        weatherName
                );

        this.backgroundColor =
                weatherColor.backgroundColor();

        this.textColor =
                weatherColor.textColor();
    }

    private String getWeatherEnglishName(
            String weatherName
    ) {
        if (weatherName.equals("비")) {
            return "Rain";
        }

        if (weatherName.equals("폭염")) {
            return "Heat";
        }

        if (weatherName.equals("미세먼지")) {
            return "Dust";
        }

        if (weatherName.equals("한파")) {
            return "Cold";
        }

        return "Sunny";
    }

    private String getWeatherMascot(
            String weatherName
    ) {
        if (weatherName.equals("비")) {
            return "☔";
        }

        if (weatherName.equals("폭염")) {
            return "☀";
        }

        if (weatherName.equals("미세먼지")) {
            return "◎";
        }

        if (weatherName.equals("한파")) {
            return "❄";
        }

        return "☘";
    }

    private Color getWeatherCardMainColor(
            String weatherName
    ) {
        if (weatherName.equals("비")) {
            return new Color(80, 170, 245);
        }

        if (weatherName.equals("폭염")) {
            return new Color(238, 198, 58);
        }

        if (weatherName.equals("미세먼지")) {
            return new Color(220, 120, 155);
        }

        if (weatherName.equals("한파")) {
            return new Color(165, 145, 225);
        }

        return new Color(90, 210, 190);
    }

    private WeatherColor getWeatherColor(
            String weatherName
    ) {
        if (weatherName.equals("비")) {
            return new WeatherColor(
                    new Color(220, 240, 255),
                    new Color(25, 70, 120)
            );
        }

        if (weatherName.equals("폭염")) {
            return new WeatherColor(
                    new Color(255, 247, 210),
                    new Color(120, 90, 20)
            );
        }

        if (weatherName.equals("한파")) {
            return new WeatherColor(
                    new Color(242, 235, 255),
                    new Color(70, 55, 120)
            );
        }

        if (weatherName.equals("미세먼지")) {
            return new WeatherColor(
                    new Color(255, 238, 244),
                    new Color(90, 60, 70)
            );
        }

        return new WeatherColor(
                new Color(228, 250, 244),
                new Color(35, 95, 85)
        );
    }

    private record WeatherColor(
            Color backgroundColor,
            Color textColor
    ) {
    }
}