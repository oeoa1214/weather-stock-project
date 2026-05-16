package org.example.service;

import java.util.Map;

public class BestStockService {

    public String findBestStock(
            Map<String, Double> returns
    ) {

        double max = -999;

        String bestStock = "";

        for (String name : returns.keySet()) {

            double value = returns.get(name);

            if (value > max) {

                max = value;
                bestStock = name;
            }
        }

        return bestStock;
    }

    public double findBestReturn(
            Map<String, Double> returns
    ) {

        double max = -999;

        for (double value : returns.values()) {

            if (value > max) {
                max = value;
            }
        }

        return max;
    }
}