package org.example.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class CardPanel extends JPanel {

    private static final Color CARD_BORDER_COLOR =
            new Color(205, 210, 215);

    private static final Color CARD_TITLE_COLOR =
            new Color(90, 95, 105);

    public CardPanel(
            String title,
            JPanel contentPanel
    ) {
        setLayout(
                new BorderLayout()
        );

        setBackground(
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

        setBorder(
                titledBorder
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );
    }
}