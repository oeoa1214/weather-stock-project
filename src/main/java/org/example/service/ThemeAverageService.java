package org.example.service;

import java.util.Map;

public class ThemeAverageService {

    public double calculateAverage(
            Map<String, Double> returns
    ) {

        double sum = 0;

        for (double value : returns.values()) {
            sum += value;
        }

        return sum / returns.size();
    }
}