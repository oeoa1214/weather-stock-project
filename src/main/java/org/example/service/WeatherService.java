package org.example.service;

import org.example.model.*;

import java.util.List;

public class WeatherService {

    public WeatherCondition selectPriorityCondition(List<WeatherCondition> conditions) {

        WeatherCondition best = conditions.get(0);

        for (WeatherCondition condition : conditions) {
            if (condition.getPriority() > best.getPriority()) {
                best = condition;
            }
        }

        return best;
    }

    public WeatherCondition createConditionByCode(int code) {

        if (code == 0) {
            return new SunnyCondition();
        } else if (code >= 51 && code <= 67) {
            return new RainCondition();
        } else if (code == 1) {
            return new HeatWaveCondition();
        } else if (code == 2) {
            return new ColdWaveCondition();
        }

        return new DustCondition();
    }

    public WeatherCondition createConditionByData(WeatherData data) {

        if (data.pm10() >= 80) {
            return new DustCondition();
        }

        if (data.precipitation() > 0) {
            return new RainCondition();
        }

        if (data.temperature() >= 30) {
            return new HeatWaveCondition();
        }

        if (data.temperature() <= 5) {
            return new ColdWaveCondition();
        }

        return new SunnyCondition();
    }
}