package org.example.service;

import org.example.model.AirCareTheme;
import org.example.model.ColdWaveNow;
import org.example.model.CoolingTheme;
import org.example.model.DeliveryTheme;
import org.example.model.DustNow;
import org.example.model.HeatWaveNow;
import org.example.model.HeatingTheme;
import org.example.model.RainNow;
import org.example.model.SunnyNow;
import org.example.model.TravelTheme;
import org.example.model.themeMom;
import org.example.model.weatherMom;

public class WeatherThemeConverter {

    public themeMom convert(
            weatherMom weather
    ) {
        if (weather instanceof RainNow) {
            return new DeliveryTheme();
        }

        if (weather instanceof SunnyNow) {
            return new TravelTheme();
        }

        if (weather instanceof DustNow) {
            return new AirCareTheme();
        }

        if (weather instanceof HeatWaveNow) {
            return new CoolingTheme();
        }

        if (weather instanceof ColdWaveNow) {
            return new HeatingTheme();
        }

        return new TravelTheme();
    }
}