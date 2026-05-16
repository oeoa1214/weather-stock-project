package org.example.service;

import org.example.model.StockSnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnMapService {

    public Map<String, Double> createReturnMap(
            List<StockSnapshot> snapshots
    ) {

        Map<String, Double> result =
                new HashMap<>();

        for (StockSnapshot snapshot : snapshots) {

            result.put(
                    snapshot.getStock().getName(),
                    snapshot.getReturnRate()
            );
        }

        return result;
    }
}