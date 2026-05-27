package org.example.view;

import org.example.model.Class6;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;

public class Class6GraphPanel extends JPanel {

    private final Class6 class6Result;

    private static final Color GRAPH_BACKGROUND =
            new Color(255, 252, 255);

    private static final Color POSITIVE_BAR =
            new Color(230, 80, 100);

    private static final Color NEGATIVE_BAR =
            new Color(80, 115, 215);

    private static final Color AXIS_COLOR =
            new Color(150, 150, 150);

    private static final Color VALUE_TEXT =
            new Color(25, 25, 25);

    private static final Color WEATHER_LABEL =
            new Color(245, 150, 205);

    public Class6GraphPanel(
            Class6 class6Result
    ) {
        this.class6Result =
                class6Result;

        setBackground(
                GRAPH_BACKGROUND
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
                    "수익률 데이터가 없습니다.",
                    20,
                    30
            );

            return;
        }

        java.awt.Graphics2D g2 =
                (java.awt.Graphics2D) g.create();

        g2.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        );

        Map<String, Double> rates =
                class6Result.getCumulativeReturnRates();

        int width =
                getWidth();

        int height =
                getHeight();

        int left =
                24;

        int right =
                20;

        int top =
                34;

        int bottom =
                48;

        int graphLeft =
                left;

        int graphRight =
                width - right;

        int graphTop =
                top;

        int graphBottom =
                height - bottom;

        int zeroY =
                (graphTop + graphBottom) / 2;

        int graphHeight =
                (graphBottom - graphTop) / 2;

        int count =
                rates.size();

        int barAreaWidth =
                (graphRight - graphLeft) / count;

        int barWidth =
                Math.min(
                        38,
                        barAreaWidth / 2
                );

        double maxAbs =
                findMaxAbs(
                        rates
                );

        if (maxAbs < 1.0) {
            maxAbs =
                    1.0;
        }

        g2.setColor(
                AXIS_COLOR
        );

        g2.drawLine(
                graphLeft,
                zeroY,
                graphRight,
                zeroY
        );

        int index =
                0;

        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            String themeName =
                    entry.getKey();

            double rate =
                    entry.getValue();

            int areaStartX =
                    graphLeft + index * barAreaWidth;

            int x =
                    areaStartX
                            + (barAreaWidth - barWidth) / 2;

            int barHeight =
                    (int) (
                            Math.abs(rate)
                                    / maxAbs
                                    * (graphHeight - 12)
                    );

            if (barHeight < 2 && rate != 0.0) {
                barHeight =
                        2;
            }

            drawBar(
                    g2,
                    x,
                    zeroY,
                    barWidth,
                    barHeight,
                    rate
            );

            drawRateText(
                    g2,
                    x,
                    zeroY,
                    barWidth,
                    barHeight,
                    rate
            );

            drawWeatherLabel(
                    g2,
                    shortThemeName(
                            themeName
                    ),
                    x + barWidth / 2,
                    height - 18
            );

            index++;
        }

        g2.dispose();
    }

    private void drawBar(
            java.awt.Graphics2D g2,
            int x,
            int zeroY,
            int barWidth,
            int barHeight,
            double rate
    ) {
        if (rate >= 0) {
            int y =
                    zeroY - barHeight;

            g2.setColor(
                    POSITIVE_BAR
            );

            g2.fillRect(
                    x,
                    y,
                    barWidth,
                    barHeight
            );

            return;
        }

        g2.setColor(
                NEGATIVE_BAR
        );

        g2.fillRect(
                x,
                zeroY,
                barWidth,
                barHeight
        );
    }

    private void drawRateText(
            java.awt.Graphics2D g2,
            int x,
            int zeroY,
            int barWidth,
            int barHeight,
            double rate
    ) {
        String text =
                String.format(
                        "%+.1f%%",
                        rate
                );

        g2.setFont(
                new Font("맑은 고딕", Font.BOLD, 11)
        );

        g2.setColor(
                VALUE_TEXT
        );

        java.awt.FontMetrics fontMetrics =
                g2.getFontMetrics();

        int textWidth =
                fontMetrics.stringWidth(
                        text
                );

        int textX =
                x + (barWidth - textWidth) / 2;

        int textY;

        if (rate >= 0) {
            textY =
                    zeroY - barHeight - 6;
        } else {
            textY =
                    zeroY + barHeight + 15;
        }

        g2.drawString(
                text,
                textX,
                textY
        );
    }

    private void drawWeatherLabel(
            java.awt.Graphics2D g2,
            String label,
            int centerX,
            int y
    ) {
        g2.setFont(
                new Font("맑은 고딕", Font.BOLD, 13)
        );

        g2.setColor(
                WEATHER_LABEL
        );

        java.awt.FontMetrics fontMetrics =
                g2.getFontMetrics();

        int textWidth =
                fontMetrics.stringWidth(
                        label
                );

        g2.drawString(
                label,
                centerX - textWidth / 2,
                y
        );
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
            return "비";
        }

        if (themeName.equals("여행·소비")) {
            return "맑음";
        }

        if (themeName.equals("공기청정·위생")) {
            return "미세먼지";
        }

        if (themeName.equals("냉방·여름소비")) {
            return "폭염";
        }

        if (themeName.equals("난방·겨울소비")) {
            return "한파";
        }

        return themeName;
    }
}