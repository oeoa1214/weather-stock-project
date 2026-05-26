package org.example.view;

import org.example.model.Class5;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Class5ScoreboardPanel extends JPanel {

    private static final Color BOARD_BLACK =
            new Color(10, 10, 10);

    private static final Color ROW_BLACK =
            new Color(20, 20, 20);

    private static final Color POSITIVE_RED =
            new Color(255, 70, 70);

    private static final Color NEGATIVE_BLUE =
            new Color(80, 150, 255);

    private static final Color RANK_YELLOW =
            new Color(255, 210, 70);

    private static final Color TEXT_GREEN =
            new Color(90, 255, 140);

    private static final Color ZERO_GRAY =
            new Color(180, 180, 180);

    public Class5ScoreboardPanel(
            Class5 class5
    ) {
        setLayout(
                new java.awt.GridLayout(5, 1, 3, 3)
        );

        setBackground(
                BOARD_BLACK
        );

        List<Map.Entry<String, Double>> entries =
                new ArrayList<>(
                        class5.getSevenDayReturnRates().entrySet()
                );

        entries.sort(
                Comparator.comparingDouble(
                        Map.Entry<String, Double>::getValue
                ).reversed()
        );

        int rank =
                1;

        for (Map.Entry<String, Double> entry : entries) {
            add(
                    createRow(
                            rank,
                            entry.getKey(),
                            entry.getValue()
                    )
            );

            rank++;
        }
    }

    private JPanel createRow(
            int rank,
            String themeName,
            double returnRate
    ) {
        JPanel row =
                new JPanel(
                        new BorderLayout(10, 0)
                );

        row.setBackground(
                ROW_BLACK
        );

        row.setBorder(
                BorderFactory.createEmptyBorder(
                        4,
                        10,
                        4,
                        10
                )
        );

        JLabel rankLabel =
                new JLabel(
                        rank + "위",
                        JLabel.CENTER
                );

        rankLabel.setPreferredSize(
                new Dimension(60, 28)
        );

        rankLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 14)
        );

        rankLabel.setForeground(
                RANK_YELLOW
        );

        JLabel themeLabel =
                new JLabel(
                        themeName,
                        JLabel.CENTER
                );

        themeLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 15)
        );

        themeLabel.setForeground(
                TEXT_GREEN
        );

        JLabel rateLabel =
                new JLabel(
                        String.format("%+.2f%%", returnRate),
                        JLabel.RIGHT
                );

        rateLabel.setPreferredSize(
                new Dimension(110, 28)
        );

        rateLabel.setFont(
                new Font("Consolas", Font.BOLD, 18)
        );

        if (returnRate > 0) {
            rateLabel.setForeground(
                    POSITIVE_RED
            );
        } else if (returnRate < 0) {
            rateLabel.setForeground(
                    NEGATIVE_BLUE
            );
        } else {
            rateLabel.setForeground(
                    ZERO_GRAY
            );
        }

        row.add(
                rankLabel,
                BorderLayout.WEST
        );

        row.add(
                themeLabel,
                BorderLayout.CENTER
        );

        row.add(
                rateLabel,
                BorderLayout.EAST
        );

        return row;
    }
}