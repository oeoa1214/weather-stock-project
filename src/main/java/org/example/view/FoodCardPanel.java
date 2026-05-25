package org.example.view;

import org.example.model.Class9;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.net.URL;

public class FoodCardPanel extends JPanel {

    private static final Color CARD_BORDER_COLOR =
            new Color(205, 210, 215);

    private static final Color CARD_TITLE_COLOR =
            new Color(90, 95, 105);

    private static final int IMAGE_WIDTH =
            250;

    private static final int IMAGE_HEIGHT =
            95;

    public FoodCardPanel(
            Class9 class9
    ) {
        if (class9 == null) {
            throw new IllegalArgumentException(
                    "class9는 null일 수 없습니다."
            );
        }

        setLayout(
                new BorderLayout(6, 6)
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
                        "9. 오늘의 추천 음식",
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("맑은 고딕", Font.BOLD, 13),
                        CARD_TITLE_COLOR
                );

        setBorder(
                titledBorder
        );

        add(
                createImageLabel(
                        class9.imagePath()
                ),
                BorderLayout.NORTH
        );

        add(
                createTextArea(
                        class9
                ),
                BorderLayout.CENTER
        );
    }

    private JLabel createImageLabel(
            String imagePath
    ) {
        JLabel imageLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        imageLabel.setBackground(
                Color.WHITE
        );

        imageLabel.setOpaque(
                true
        );

        ImageIcon imageIcon =
                loadImageIcon(
                        imagePath
                );

        if (imageIcon == null) {
            imageLabel.setText(
                    "이미지 없음: " + imagePath
            );

            imageLabel.setFont(
                    new Font("맑은 고딕", Font.PLAIN, 12)
            );

            imageLabel.setForeground(
                    Color.GRAY
            );

            return imageLabel;
        }

        Image scaledImage =
                imageIcon.getImage().getScaledInstance(
                        IMAGE_WIDTH,
                        IMAGE_HEIGHT,
                        Image.SCALE_SMOOTH
                );

        imageLabel.setIcon(
                new ImageIcon(
                        scaledImage
                )
        );

        return imageLabel;
    }

    private ImageIcon loadImageIcon(
            String imagePath
    ) {
        if (imagePath == null || imagePath.isBlank()) {
            return null;
        }

        URL imageUrl =
                getClass().getResource(
                        imagePath
                );

        if (imageUrl != null) {
            return new ImageIcon(
                    imageUrl
            );
        }

        File imageFile =
                new File(
                        "src/main/resources" + imagePath
                );

        if (imageFile.exists()) {
            return new ImageIcon(
                    imageFile.getAbsolutePath()
            );
        }

        System.out.println(
                "음식 이미지 찾기 실패"
        );

        System.out.println(
                "classpath 경로: " + imagePath
        );

        System.out.println(
                "file 경로: " + imageFile.getAbsolutePath()
        );

        return null;
    }

    private JTextArea createTextArea(
            Class9 class9
    ) {
        JTextArea textArea =
                new JTextArea(
                        createClass9Text(
                                class9
                        )
                );

        textArea.setFont(
                new Font("맑은 고딕", Font.PLAIN, 12)
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

        textArea.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        6,
                        4,
                        6
                )
        );

        return textArea;
    }

    private String createClass9Text(
            Class9 class9
    ) {
        return "날씨: "
                + class9.weatherName()
                + "\n"
                + "추천 음식: "
                + class9.foodName()
                + "\n\n"
                + "설명:\n"
                + class9.description()
                + "\n\n"
                + "태그: "
                + class9.tags();
    }
}