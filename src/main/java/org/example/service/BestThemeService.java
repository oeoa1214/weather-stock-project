package org.example.service;

import org.example.model.ThemePerformance;

import java.util.List;

public class BestThemeService {

    public ThemePerformance findBestTheme(
            List<ThemePerformance> performances
    ) {

        ThemePerformance best =
                performances.get(0);

        for (ThemePerformance performance : performances) {

            if (performance.averageReturn()
                    > best.averageReturn()) {

                best = performance;
            }
        }

        return best;
    }
}