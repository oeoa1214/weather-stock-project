package org.example.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("실시간 날씨 기반 주식 테마 추천 분석 시스템");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel =
                new JLabel(
                        "Weather Stock Analyzer",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font("맑은 고딕", Font.BOLD, 28)
        );

        add(titleLabel);

        setVisible(true);
    }
}