package org.example.service;

import org.example.model.*;

public class ThemeRecommendService {

    public ThemeStrategy recommendTheme(
            WeatherCondition condition) {

        if (condition instanceof RainCondition) {
            return new DeliveryTheme();
        }

        if (condition instanceof SunnyCondition) {
            return new TravelTheme();
        }

        if (condition instanceof HeatWaveCondition) {
            return new CoolingTheme();
        }

        if (condition instanceof ColdWaveCondition) {
            return new HeatingTheme();
        }

        return new AirCareTheme();
    }
}