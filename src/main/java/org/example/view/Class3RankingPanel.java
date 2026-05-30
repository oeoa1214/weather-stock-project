package org.example.view;

import org.example.model.Class3;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

public class Class3RankingPanel extends JPanel {

    private static final String CHARACTER_PATH =
            "data/character/teacher1.png";

    private static final String FALLBACK_CHARACTER_PATH =
            "data/character/teacher.png";

    private static final Color BACKGROUND =
            Color.WHITE;

    private static final Color ROW_BACKGROUND =
            new Color(248, 252, 255);

    private static final Color ROW_BORDER =
            new Color(205, 230, 245);

    private static final Color NAME_COLOR =
            new Color(25, 45, 70);

    private static final Color PRICE_COLOR =
            new Color(35, 35, 40);

    private static final Color POSITIVE_RED =
            new Color(220, 75, 90);

    private static final Color NEGATIVE_BLUE =
            new Color(70, 120, 220);

    private static final Color ZERO_GRAY =
            new Color(120, 120, 120);

    private static final Color SPEECH_BORDER =
            new Color(110, 200, 230);

    private static final Color SPEECH_TEXT =
            new Color(40, 55, 80);

    public Class3RankingPanel(
            List<Class3> class3Result
    ) {
        setLayout(
                new BorderLayout(8, 0)
        );

        setBackground(
                BACKGROUND
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        6,
                        6,
                        6,
                        6
                )
        );

        JPanel stockListPanel =
                new JPanel(
                        new GridLayout(5, 1, 4, 4)
                );

        stockListPanel.setBackground(
                BACKGROUND
        );

        int count =
                Math.min(
                        5,
                        class3Result.size()
                );

        for (int i = 0; i < count; i++) {
            stockListPanel.add(
                    createStockRow(
                            class3Result.get(i)
                    )
            );
        }

        for (int i = count; i < 5; i++) {
            stockListPanel.add(
                    createEmptyRow()
            );
        }

        add(
                stockListPanel,
                BorderLayout.CENTER
        );

        add(
                createCharacterPanel(),
                BorderLayout.EAST
        );
    }

    private JPanel createStockRow(
            Class3 stock
    ) {
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
                                7,
                                4,
                                7
                        )
                )
        );

        JLabel nameLabel =
                new JLabel(
                        stock.name(),
                        SwingConstants.LEFT
                );

        nameLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 14)
        );

        nameLabel.setForeground(
                NAME_COLOR
        );

        JPanel valuePanel =
                new JPanel(
                        new GridLayout(1, 2, 2, 0)
                );

        valuePanel.setOpaque(
                false
        );

        valuePanel.setPreferredSize(
                new Dimension(
                        118,
                        30
                )
        );

        JLabel priceLabel =
                new JLabel(
                        String.format(
                                "%,.0f원",
                                stock.currentPrice()
                        ),
                        SwingConstants.LEFT
                );

        priceLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 11)
        );

        priceLabel.setForeground(
                PRICE_COLOR
        );

        JLabel rateLabel =
                new JLabel(
                        String.format(
                                "%+.2f%%",
                                stock.returnRate()
                        ),
                        SwingConstants.RIGHT
                );

        rateLabel.setFont(
                new Font("Consolas", Font.BOLD, 12)
        );

        rateLabel.setForeground(
                getRateColor(
                        stock.returnRate()
                )
        );

        valuePanel.add(
                priceLabel
        );

        valuePanel.add(
                rateLabel
        );

        row.add(
                nameLabel,
                BorderLayout.CENTER
        );

        row.add(
                valuePanel,
                BorderLayout.EAST
        );

        return row;
    }

    private JPanel createCharacterPanel() {
        JPanel panel =
                new JPanel(
                        new BorderLayout(2, 4)
                );

        panel.setOpaque(
                false
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        0,
                        0
                )
        );

        panel.setPreferredSize(
                new Dimension(
                        135,
                        0
                )
        );

        JLabel characterLabel =
                createCharacterLabel();

        JLabel speechLabel =
                new JLabel(
                        createSpeechText(),
                        SwingConstants.CENTER
                );

        speechLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 10)
        );

        speechLabel.setForeground(
                SPEECH_TEXT
        );

        speechLabel.setOpaque(
                true
        );

        speechLabel.setBackground(
                Color.WHITE
        );

        speechLabel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                SPEECH_BORDER,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                4,
                                4,
                                4,
                                4
                        )
                )
        );

        panel.add(
                characterLabel,
                BorderLayout.CENTER
        );

        panel.add(
                speechLabel,
                BorderLayout.SOUTH
        );

        return panel;
    }

    private JLabel createCharacterLabel() {
        ImageIcon originalIcon =
                new ImageIcon(
                        CHARACTER_PATH
                );

        if (originalIcon.getIconWidth() <= 0) {
            originalIcon =
                    new ImageIcon(
                            FALLBACK_CHARACTER_PATH
                    );
        }

        if (originalIcon.getIconWidth() <= 0) {
            JLabel fallback =
                    new JLabel(
                            "📊",
                            SwingConstants.CENTER
                    );

            fallback.setFont(
                    new Font("Segoe UI Emoji", Font.PLAIN, 72)
            );

            fallback.setForeground(
                    new Color(90, 175, 235)
            );

            return fallback;
        }

        Image scaledImage =
                originalIcon.getImage()
                        .getScaledInstance(
                                130,
                                130,
                                Image.SCALE_SMOOTH
                        );

        return new JLabel(
                new ImageIcon(
                        scaledImage
                ),
                SwingConstants.CENTER
        );
    }

    private String createSpeechText() {
        return "<html><center>선생님~<br>오늘의 추천<br>날씨 테마입니다.</center></html>";
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
                BorderFactory.createLineBorder(
                        ROW_BORDER,
                        1
                )
        );

        return row;
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