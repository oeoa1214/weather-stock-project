package org.example.service;

import java.util.List;

public class ReturnRateService {

    public double calculateReturnRate(
            List<Double> prices
    ) {

        if (prices.size() < 2) {
            return 0;
        }

        double firstPrice =
                prices.get(0);

        double lastPrice =
                prices.get(prices.size() - 1);

        return ((lastPrice - firstPrice)
                / firstPrice) * 100;
    }
}