package org.example.service;

import org.example.model.ColdWaveNow;
import org.example.model.DustNow;
import org.example.model.HeatWaveNow;
import org.example.model.RainNow;
import org.example.model.SunnyNow;
import org.example.model.weatherMom;

public class Judge2 {

    public weatherMom judge(
            double temperature,
            double precipitation,
            double pm10
    ) {

        if (pm10 >= 60) {
            return new DustNow();
        }

        if (precipitation >= 5) {
            return new RainNow();
        }

        if (temperature >= 26.0) {
            return new HeatWaveNow();
        }

        if (temperature <= 5) {
            return new ColdWaveNow();
        }

        return new SunnyNow();
    }
}