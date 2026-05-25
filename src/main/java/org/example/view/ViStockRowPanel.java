package org.example.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class ViStockRowPanel extends JPanel {

    private final JLabel label =
            new JLabel();

    private final ViTextMaker textMaker =
            new ViTextMaker();

    private final ViColorDecider colorDecider =
            new ViColorDecider();

    public ViStockRowPanel() {
        setLayout(
                new GridLayout(1, 1)
        );

        setBackground(
                Color.WHITE
        );

        label.setFont(
                new Font("맑은 고딕", Font.BOLD, 13)
        );

        add(
                label
        );
    }

    public void updateRow(
            int number,
            String stockName,
            double currentPrice,
            double changeRate
    ) {
        label.setText(
                textMaker.makeStockText(
                        number,
                        stockName,
                        currentPrice,
                        changeRate
                )
        );

        label.setForeground(
                colorDecider.decide(
                        changeRate
                )
        );
    }
}