package org.example.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class ThemeCharacterPanel extends JPanel {

    private static final String CHARACTER_PATH =
            "data/character/teacher.png";

    public ThemeCharacterPanel(
            String themeName
    ) {
        setLayout(
                new BorderLayout(4, 4)
        );

        setOpaque(
                false
        );

        JLabel characterLabel =
                createCharacterLabel();

        JLabel speechBubble =
                createSpeechBubble(
                        themeName
                );

        add(
                characterLabel,
                BorderLayout.CENTER
        );

        add(
                speechBubble,
                BorderLayout.SOUTH
        );
    }

    private JLabel createCharacterLabel() {
        ImageIcon originalIcon =
                new ImageIcon(
                        CHARACTER_PATH
                );

        if (originalIcon.getIconWidth() <= 0) {
            JLabel fallback =
                    new JLabel(
                            "☂",
                            SwingConstants.CENTER
                    );

            fallback.setFont(
                    new Font("Segoe UI Emoji", Font.PLAIN, 64)
            );

            return fallback;
        }

        Image scaledImage =
                originalIcon.getImage()
                        .getScaledInstance(
                                200,
                                200,
                                Image.SCALE_SMOOTH
                        );

        return new JLabel(
                new ImageIcon(
                        scaledImage
                ),
                SwingConstants.CENTER
        );
    }

    private JLabel createSpeechBubble(
            String themeName
    ) {
        JLabel label =
                new JLabel(
                        getSpeechText(
                                themeName
                        ),
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font("맑은 고딕", Font.BOLD, 11)
        );

        label.setForeground(
                new Color(35, 45, 65)
        );

        label.setOpaque(
                true
        );

        label.setBackground(
                new Color(255, 255, 255, 220)
        );

        label.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                getThemeColor(
                                        themeName
                                ),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                6,
                                5,
                                6
                        )
                )
        );

        return label;
    }

    private String getSpeechText(
            String themeName
    ) {
        if (themeName.contains("편의점")) {
            return "<html><center>선생님! 오늘의 추천종목이에요!</center></html>";
        }

        if (themeName.contains("여행")) {
            return "<html><center>선생님! 오늘의 추천종목이에요!</center></html>";
        }

        if (themeName.contains("공기")) {
            return "<html><center>선생님! 오늘의 추천종목이에요!</center></html>";
        }

        if (themeName.contains("냉방")) {
            return "<html><center>선생님! 오늘의 추천종목이에요!</center></html>";
        }

        if (themeName.contains("난방")) {
            return "<html><center>선생님! 오늘의 추천종목이에요!</center></html>";
        }

        return "<html><center>선생님, 오늘 조건에 맞는<br>추천 테마를 확인해보세요.</center></html>";
    }

    private Color getThemeColor(
            String themeName
    ) {
        if (themeName.contains("편의점")) {
            return new Color(80, 170, 245);
        }

        if (themeName.contains("여행")) {
            return new Color(70, 190, 150);
        }

        if (themeName.contains("공기")) {
            return new Color(190, 120, 170);
        }

        if (themeName.contains("냉방")) {
            return new Color(80, 150, 220);
        }

        if (themeName.contains("난방")) {
            return new Color(210, 120, 70);
        }

        return new Color(120, 140, 160);
    }
}