package org.example.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StockCacheService {

    private static final String FILE_PATH =
            "data/stock_cache.csv";

    // 종목 가격 캐시 저장
    public void savePrices(String symbol,
                           List<Double> prices) {

        try (FileWriter fw =
                     new FileWriter(FILE_PATH)) {

            fw.write(symbol);

            for (Double p : prices) {
                fw.write("," + p);
            }

            fw.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}