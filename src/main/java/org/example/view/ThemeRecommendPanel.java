package org.example.view;
//2번패널
//2번패널
//2번패널
//2번패널

import org.example.model.Class4;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ThemeRecommendPanel extends JPanel {

    private static final Color CARD_TITLE_COLOR =
            new Color(90, 95, 105);

    public ThemeRecommendPanel(
            Class4 class4
    ) {
        setLayout(
                new BorderLayout(10, 10)
        );

        setOpaque(
                false
        );

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(205, 210, 215, 150),
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

        textArea.setEditable(
                false
        );

        textArea.setLineWrap(
                true
        );

        textArea.setWrapStyleWord(
                true
        );

        textArea.setOpaque(
                false
        );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 0)
                );

        centerPanel.setOpaque(
                false
        );

        centerPanel.add(
                textArea,
                BorderLayout.CENTER
        );

        centerPanel.add(
                new ThemeCharacterPanel(
                        class4.themeName()
                ),
                BorderLayout.EAST
        );

        add(
                title,
                BorderLayout.NORTH
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    @Override
    protected void paintComponent(
            Graphics g
    ) {
        super.paintComponent(
                g
        );

        java.awt.Graphics2D g2 =
                (java.awt.Graphics2D) g.create();

        g2.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(
                new Color(255, 255, 255, 175)
        );

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                22,
                22
        );

        g2.dispose();
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
                + "%\n";
    }
}