package org.example.service;

import org.example.model.Stock;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StockCacheService {

    private static final String FILE_PATH =
            "data/stock_cache.csv";

    private final StockPriceService priceService =
            new StockPriceService();

    // 종목 가격 캐시 저장
    public void cacheStock(
            Stock stock,
            String json
    ) {

        List<Double> prices =
                priceService.getClosePrices(json);

        try (FileWriter fw =
                     new FileWriter(FILE_PATH, true)) {

            fw.write(stock.getSymbol());

            for (Double p : prices) {
                fw.write("," + p);
            }

            fw.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}