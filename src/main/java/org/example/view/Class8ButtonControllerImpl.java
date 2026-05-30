package org.example.view;

public final class Class8ButtonControllerImpl
        implements Class8ButtonController {

    private final Class8AnalysisPanel panel;

    public Class8ButtonControllerImpl(
            Class8AnalysisPanel panel
    ) {
        if (panel == null) {
            throw new IllegalArgumentException(
                    "Class8AnalysisPanel은 null일 수 없습니다."
            );
        }

        this.panel =
                panel;
    }

    @Override
    public void clickDummyButton() {
        panel.handleDummyButton();
    }

    @Override
    public void clickKospiButton() {
        panel.handleKospiButton();
    }

    @Override
    public void clickTemperatureButton() {
        panel.handleTemperatureButton();
    }

    @Override
    public void clickRegionButton() {
        panel.handleRegionButton();
    }

    @Override
    public void clickDefaultButton() {
        panel.handleDefaultButton();
    }
}