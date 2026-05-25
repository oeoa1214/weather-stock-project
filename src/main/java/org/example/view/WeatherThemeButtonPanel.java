package org.example.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class WeatherThemeButtonPanel extends JPanel {

    private final List<JButton> buttons =
            new ArrayList<>();

    private final Function<String, Color> selectedColorProvider;

    private JButton selectedButton;

    private static final Color CONTROL_BG =
            new Color(242, 244, 247);

    private static final Color BUTTON_BG =
            new Color(232, 235, 239);

    private static final Color BUTTON_FG =
            new Color(70, 75, 85);

    private static final Color BORDER_COLOR =
            new Color(205, 210, 215);

    public WeatherThemeButtonPanel(
            Consumer<String> weatherSelectHandler,
            Function<String, Color> selectedColorProvider
    ) {
        if (weatherSelectHandler == null) {
            throw new IllegalArgumentException(
                    "weatherSelectHandler는 null일 수 없습니다."
            );
        }

        if (selectedColorProvider == null) {
            throw new IllegalArgumentException(
                    "selectedColorProvider는 null일 수 없습니다."
            );
        }

        this.selectedColorProvider =
                selectedColorProvider;

        setLayout(
                new GridLayout(2, 3, 10, 10)
        );

        setBackground(
                CONTROL_BG
        );

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1),
                        BorderFactory.createEmptyBorder(14, 14, 14, 14)
                )
        );

        addWeatherButton("↺", "실시간", weatherSelectHandler);
        addWeatherButton("☘", "맑음", weatherSelectHandler);
        addWeatherButton("☔", "비", weatherSelectHandler);
        addWeatherButton("☀", "폭염", weatherSelectHandler);
        addWeatherButton("❄", "한파", weatherSelectHandler);
        addWeatherButton("🌫", "미세먼지", weatherSelectHandler);
    }

    private void addWeatherButton(
            String icon,
            String weatherName,
            Consumer<String> weatherSelectHandler
    ) {
        JButton button =
                new JButton(icon);

        button.setToolTipText(weatherName);

        button.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 30)
        );

        button.setFocusPainted(false);
        button.setBackground(BUTTON_BG);
        button.setForeground(BUTTON_FG);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 224, 229),
                                1
                        ),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );

        button.addActionListener(
                event -> {
                    selectButton(
                            button,
                            weatherName
                    );

                    weatherSelectHandler.accept(
                            weatherName
                    );
                }
        );

        buttons.add(button);
        add(button);
    }

    private void selectButton(
            JButton selected,
            String weatherName
    ) {
        for (JButton button : buttons) {
            button.setBackground(BUTTON_BG);
            button.setForeground(BUTTON_FG);
        }

        selectedButton =
                selected;

        selectedButton.setBackground(
                selectedColorProvider.apply(weatherName)
        );

        selectedButton.setForeground(
                Color.WHITE
        );
    }
}