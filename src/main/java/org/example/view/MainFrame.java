package org.example.view;

import org.example.controller.AppController;
import org.example.model.Class1;
import org.example.model.Class3;
import org.example.model.Class4;
import org.example.model.Class5;
import org.example.model.Class6;
import org.example.model.Class9;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {

    private final AppController appController;
    private final Class1 class1;

    private JPanel weatherCardPanel;
    private JPanel rootPanel;
    private JPanel gridPanel;
    private JLabel titleLabel;

    private Color backgroundColor;
    private Color textColor;

    private static final Color CARD_BORDER_COLOR =
            new Color(205, 210, 215);

    private static final Color CARD_TITLE_COLOR =
            new Color(90, 95, 105);

    public MainFrame(
            Class1 class1,
            Class4 class4,
            List<Class3> class3Result,
            Class5 class5Result,
            Class6 class6Result,
            Class9 class9,
            AppController appController
    ) {
        this.class1 =
                class1;

        this.appController =
                appController;

        applyColorFields(
                class1.condition().getName()
        );

        setTitle("실시간 날씨 기반 주식 테마 추천 분석 의사결정 시스템");
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
                        "Weather-Stock Dashboard",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 28)
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
                createThemeCard(
                        class4
                )
        );

        gridPanel.add(
                createCard(
                        "3. 추천 종목 Top 5",
                        createClass3Text(
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
                createCard(
                        "5. 테마별 최근 7일 성과",
                        createClass5Text(
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
                new ViSimulationPanel()
        );

        gridPanel.add(
                new CardPanel(
                        "8. 1년 날씨 데이터 기반 수익률 예측",
                        new DummyModePanel(
                                appController,
                                this::applyDummyStepResult
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
                new Font("맑은 고딕", Font.BOLD, 13)
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
                new Font("맑은 고딕", Font.BOLD, 42)
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
                new Font("맑은 고딕", Font.BOLD, 18)
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

        centerPanel.add(
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

    private JLabel createWeatherDetailLabel(
            String text
    ) {
        JLabel label =
                new JLabel(
                        text,
                        SwingConstants.LEFT
                );

        label.setFont(
                new Font("맑은 고딕", Font.PLAIN, 12)
        );

        label.setForeground(
                Color.WHITE
        );

        return label;
    }

    private JPanel createThemeCard(
            Class4 class4
    ) {
        Color themeColor =
                getThemeDarkColor(
                        class4.themeName()
                );

        JPanel panel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                CARD_BORDER_COLOR,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "2. 현재 날씨 기반 대표 추천",
                        SwingConstants.LEFT
                );

        title.setFont(
                new Font("맑은 고딕", Font.BOLD, 13)
        );

        title.setForeground(
                CARD_TITLE_COLOR
        );

        JTextArea textArea =
                new JTextArea(
                        createClass4Text(
                                class4
                        )
                );

        textArea.setFont(
                new Font("맑은 고딕", Font.PLAIN, 14)
        );

        textArea.setForeground(
                new Color(35, 35, 35)
        );

        textArea.setBackground(
                Color.WHITE
        );

        textArea.setEditable(
                false
        );

        textArea.setLineWrap(
                true
        );

        textArea.setWrapStyleWord(
                true
        );

        JLabel icon =
                new JLabel(
                        getThemeIcon(
                                class4.themeName()
                        ),
                        SwingConstants.CENTER
                );

        icon.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 44)
        );

        icon.setForeground(
                themeColor
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                textArea,
                BorderLayout.CENTER
        );

        panel.add(
                icon,
                BorderLayout.EAST
        );

        return panel;
    }

    private JScrollPane createCard(
            String title,
            String content
    ) {
        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        TitledBorder titledBorder =
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                CARD_BORDER_COLOR,
                                1
                        ),
                        title,
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("맑은 고딕", Font.BOLD, 13),
                        CARD_TITLE_COLOR
                );

        panel.setBorder(
                titledBorder
        );

        JTextArea textArea =
                new JTextArea(
                        content
                );

        textArea.setFont(
                new Font("맑은 고딕", Font.PLAIN, 14)
        );

        textArea.setForeground(
                new Color(35, 35, 35)
        );

        textArea.setEditable(
                false
        );

        textArea.setLineWrap(
                true
        );

        textArea.setWrapStyleWord(
                true
        );

        textArea.setBackground(
                Color.WHITE
        );

        panel.add(
                textArea,
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

    private JScrollPane createClass6Card(
            String title,
            Class6 class6Result
    ) {
        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        TitledBorder titledBorder =
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                CARD_BORDER_COLOR,
                                1
                        ),
                        title,
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("맑은 고딕", Font.BOLD, 13),
                        CARD_TITLE_COLOR
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

    private String createClass4Text(
            Class4 class4
    ) {
        return "추천 테마: "
                + class4.themeName()
                + "\n"
                + "대표 추천 종목: "
                + class4.stockName()
                + "\n"
                + "현재가: "
                + String.format("%,.0f원", class4.currentPrice())
                + "\n"
                + "수익률: "
                + String.format("%+.2f", class4.returnRate())
                + "%\n\n"
                + "추천 이유:\n"
                + class4.reason();
    }

    private String createClass3Text(
            List<Class3> class3Result
    ) {
        StringBuilder builder =
                new StringBuilder();

        for (int i = 0; i < class3Result.size(); i++) {
            Class3 stock =
                    class3Result.get(i);

            builder.append(i + 1)
                    .append(". ")
                    .append(stock.name())
                    .append("\n")
                    .append("   심볼: ")
                    .append(stock.symbol())
                    .append("\n")
                    .append("   테마: ")
                    .append(stock.theme())
                    .append("\n")
                    .append("   현재가: ")
                    .append(String.format("%,.0f원", stock.currentPrice()))
                    .append("\n")
                    .append("   수익률: ")
                    .append(String.format("%+.2f", stock.returnRate()))
                    .append("%\n")
                    .append("   이유: ")
                    .append(stock.reason())
                    .append("\n\n");
        }

        return builder.toString();
    }

    private String createClass5Text(
            Class5 class5Result
    )
    {

        StringBuilder builder =
                new StringBuilder();

        for (Map.Entry<String, Double> entry
                : class5Result.getSevenDayReturnRates().entrySet()) {

            builder.append(entry.getKey())
                    .append(" : ")
                    .append(String.format("%+.2f", entry.getValue()))
                    .append("%\n");
        }

        return builder.toString();
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
                createCard(
                        "5. 테마별 최근 7일 성과",
                        createClass5Text(
                                result.class5Result()
                        )
                )
        );

        gridPanel.revalidate();
        gridPanel.repaint();
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
            return "★";
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

    private Color getThemeDarkColor(
            String themeName
    ) {
        if (themeName.contains("편의점")) {
            return new Color(20, 95, 170);
        }

        if (themeName.contains("여행")) {
            return new Color(25, 135, 105);
        }

        if (themeName.contains("냉방")) {
            return new Color(20, 115, 190);
        }

        if (themeName.contains("난방")) {
            return new Color(190, 85, 35);
        }

        if (themeName.contains("공기")) {
            return new Color(120, 85, 150);
        }

        return new Color(40, 70, 110);
    }

    private String getThemeIcon(
            String themeName
    ) {
        if (themeName.contains("편의점")) {
            return "🛒";
        }

        if (themeName.contains("여행")) {
            return "🛍";
        }

        if (themeName.contains("냉방")) {
            return "❄";
        }

        if (themeName.contains("난방")) {
            return "🔥";
        }

        if (themeName.contains("공기")) {
            return "🌫";
        }

        return "★";
    }

    private class Class6GraphPanel extends JPanel {

        private final Class6 class6Result;

        private Class6GraphPanel(
                Class6 class6Result
        ) {
            this.class6Result =
                    class6Result;

            setBackground(
                    Color.WHITE
            );
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {
            super.paintComponent(
                    g
            );

            if (class6Result == null
                    || class6Result.getCumulativeReturnRates().isEmpty()) {
                g.setColor(
                        Color.BLACK
                );

                g.drawString(
                        "누적수익률 데이터가 없습니다.",
                        20,
                        30
                );

                return;
            }

            Map<String, Double> rates =
                    class6Result.getCumulativeReturnRates();

            int width =
                    getWidth();

            int height =
                    getHeight();

            int top =
                    30;

            int bottom =
                    height - 45;

            int zeroY =
                    (top + bottom) / 2;

            int graphHeight =
                    (bottom - top) / 2;

            int count =
                    rates.size();

            int barAreaWidth =
                    width / count;

            int barWidth =
                    34;

            double maxAbs =
                    findMaxAbs(
                            rates
                    );

            if (maxAbs < 1.0) {
                maxAbs =
                        1.0;
            }

            g.setColor(
                    Color.GRAY
            );

            g.drawLine(
                    10,
                    zeroY,
                    width - 10,
                    zeroY
            );

            g.setFont(
                    new Font("맑은 고딕", Font.BOLD, 11)
            );

            int index =
                    0;

            for (Map.Entry<String, Double> entry : rates.entrySet()) {
                String themeName =
                        entry.getKey();

                double rate =
                        entry.getValue();

                int barHeight =
                        (int) (Math.abs(rate) / maxAbs * graphHeight);

                int x =
                        index * barAreaWidth
                                + (barAreaWidth - barWidth) / 2;

                if (rate >= 0) {
                    int y =
                            zeroY - barHeight;

                    g.setColor(
                            new Color(210, 60, 60)
                    );

                    g.fillRect(
                            x,
                            y,
                            barWidth,
                            barHeight
                    );

                    g.setColor(
                            Color.BLACK
                    );

                    g.drawString(
                            String.format("%+.1f%%", rate),
                            x - 8,
                            y - 5
                    );
                } else {
                    int y =
                            zeroY;

                    g.setColor(
                            new Color(60, 100, 210)
                    );

                    g.fillRect(
                            x,
                            y,
                            barWidth,
                            barHeight
                    );

                    g.setColor(
                            Color.BLACK
                    );

                    g.drawString(
                            String.format("%+.1f%%", rate),
                            x - 8,
                            y + barHeight + 15
                    );
                }

                g.setColor(
                        Color.BLACK
                );

                g.drawString(
                        shortThemeName(
                                themeName
                        ),
                        index * barAreaWidth + 6,
                        height - 18
                );

                index++;
            }
        }

        private double findMaxAbs(
                Map<String, Double> rates
        ) {
            double max =
                    0.0;

            for (double rate : rates.values()) {
                double abs =
                        Math.abs(
                                rate
                        );

                if (abs > max) {
                    max =
                            abs;
                }
            }

            return max;
        }

        private String shortThemeName(
                String themeName
        ) {
            if (themeName.equals("편의점·간편식")) {
                return "편의점";
            }

            if (themeName.equals("여행·소비")) {
                return "여행";
            }

            if (themeName.equals("공기청정·위생")) {
                return "공기";
            }

            if (themeName.equals("냉방·여름소비")) {
                return "냉방";
            }

            if (themeName.equals("난방·겨울소비")) {
                return "난방";
            }

            return themeName;
        }
    }

    private record WeatherColor(
            Color backgroundColor,
            Color textColor
    ) {
    }
}