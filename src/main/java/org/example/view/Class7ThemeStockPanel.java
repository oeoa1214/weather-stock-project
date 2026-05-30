package org.example.view;

import org.example.model.Class6;
import org.example.model.Class7;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

public class Class7ThemeStockPanel extends JPanel {

    private static final Color BACKGROUND =
            Color.WHITE;

    private static final Color ROW_BACKGROUND =
            new Color(252, 252, 252);

    private static final Color ROW_BORDER =
            new Color(225, 225, 225);

    private static final Color THEME_COLOR =
            new Color(80, 80, 85);

    private static final Color STOCK_COLOR =
            new Color(35, 35, 40);

    private static final Color PRICE_COLOR =
            new Color(65, 65, 70);

    private static final Color POSITIVE_RED =
            new Color(220, 75, 90);

    private static final Color NEGATIVE_BLUE =
            new Color(70, 120, 220);

    private static final Color ZERO_GRAY =
            new Color(120, 120, 120);

    public Class7ThemeStockPanel(
            List<Class7> class7Result
    ) {
        this(
                class7Result,
                (Map<String, Double>) null,
                "테마별 대표 종목 현황"
        );
    }

    public Class7ThemeStockPanel(
            List<Class7> class7Result,
            Class6 class6Result,
            String title
    ) {
        this(
                class7Result,
                extractRateMap(
                        class6Result
                ),
                title
        );
    }

    public Class7ThemeStockPanel(
            List<Class7> class7Result,
            Map<String, Double> appliedRateMap,
            String title
    ) {
        if (class7Result == null || class7Result.isEmpty()) {
            throw new IllegalArgumentException(
                    "Class7 결과 목록은 비어 있을 수 없습니다."
            );
        }

        setLayout(
                new BorderLayout(6, 6)
        );

        setBackground(
                BACKGROUND
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        8,
                        8
                )
        );

        JLabel headerLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        headerLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 13)
        );

        headerLabel.setForeground(
                new Color(45, 45, 50)
        );

        JPanel rowPanel =
                new JPanel(
                        new GridLayout(5, 1, 4, 4)
                );

        rowPanel.setBackground(
                BACKGROUND
        );

        int count =
                Math.min(
                        5,
                        class7Result.size()
                );

        for (int i = 0; i < count; i++) {
            rowPanel.add(
                    createRow(
                            class7Result.get(i),
                            appliedRateMap
                    )
            );
        }

        for (int i = count; i < 5; i++) {
            rowPanel.add(
                    createEmptyRow()
            );
        }

        add(
                headerLabel,
                BorderLayout.NORTH
        );

        add(
                rowPanel,
                BorderLayout.CENTER
        );
    }

    private static Map<String, Double> extractRateMap(
            Class6 class6Result
    ) {
        if (class6Result == null) {
            return null;
        }

        return class6Result.getCumulativeReturnRates();
    }

    private JPanel createRow(
            Class7 class7,
            Map<String, Double> appliedRateMap
    ) {
        double appliedRate =
                findAppliedRate(
                        class7,
                        appliedRateMap
                );

        double displayPrice =
                class7.currentPrice()
                        * (1.0 + appliedRate / 100.0);

        JPanel row =
                new JPanel(
                        new BorderLayout(6, 0)
                );

        row.setBackground(
                ROW_BACKGROUND
        );

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                ROW_BORDER,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                4,
                                6,
                                4,
                                6
                        )
                )
        );

        JPanel leftPanel =
                new JPanel(
                        new BorderLayout(4, 0)
                );

        leftPanel.setOpaque(
                false
        );

        JLabel themeLabel =
                new JLabel(
                        shortThemeName(
                                class7.themeName()
                        ),
                        SwingConstants.LEFT
                );

        themeLabel.setPreferredSize(
                new Dimension(
                        78,
                        24
                )
        );

        themeLabel.setFont(
                new Font("맑은 고딕", Font.PLAIN, 10)
        );

        themeLabel.setForeground(
                THEME_COLOR
        );

        JLabel stockLabel =
                new JLabel(
                        class7.stockName(),
                        SwingConstants.LEFT
                );

        stockLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 12)
        );

        stockLabel.setForeground(
                STOCK_COLOR
        );

        leftPanel.add(
                themeLabel,
                BorderLayout.WEST
        );

        leftPanel.add(
                stockLabel,
                BorderLayout.CENTER
        );

        JPanel rightPanel =
                new JPanel(
                        new GridLayout(1, 2, 4, 0)
                );

        rightPanel.setOpaque(
                false
        );

        rightPanel.setPreferredSize(
                new Dimension(
                        140,
                        24
                )
        );

        JLabel priceLabel =
                new JLabel(
                        String.format(
                                "%,.0f원",
                                displayPrice
                        ),
                        SwingConstants.RIGHT
                );

        priceLabel.setFont(
                new Font("맑은 고딕", Font.PLAIN, 10)
        );

        priceLabel.setForeground(
                PRICE_COLOR
        );

        JLabel rateLabel =
                new JLabel(
                        String.format(
                                "%+.2f%%",
                                appliedRate
                        ),
                        SwingConstants.RIGHT
                );

        rateLabel.setFont(
                new Font("Consolas", Font.BOLD, 11)
        );

        rateLabel.setForeground(
                getRateColor(
                        appliedRate
                )
        );

        rightPanel.add(
                priceLabel
        );

        rightPanel.add(
                rateLabel
        );

        row.add(
                leftPanel,
                BorderLayout.CENTER
        );

        row.add(
                rightPanel,
                BorderLayout.EAST
        );

        return row;
    }

    private JPanel createEmptyRow() {
        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setBackground(
                ROW_BACKGROUND
        );

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                ROW_BORDER,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                4,
                                6,
                                4,
                                6
                        )
                )
        );

        return row;
    }

    private double findAppliedRate(
            Class7 class7,
            Map<String, Double> appliedRateMap
    ) {
        if (appliedRateMap == null || appliedRateMap.isEmpty()) {
            return class7.returnRate();
        }

        if (!appliedRateMap.containsKey(
                class7.themeName()
        )) {
            return class7.returnRate();
        }

        return appliedRateMap.get(
                class7.themeName()
        );
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
            return "위생";
        }

        if (themeName.equals("냉방·여름소비")) {
            return "냉방";
        }

        if (themeName.equals("난방·겨울소비")) {
            return "난방";
        }

        return themeName;
    }

    private Color getRateColor(
            double rate
    ) {
        if (rate > 0) {
            return POSITIVE_RED;
        }

        if (rate < 0) {
            return NEGATIVE_BLUE;
        }

        return ZERO_GRAY;
    }
}