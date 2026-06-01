package org.example.util;

import org.example.model.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class StockFileReader {

    private static final String FILE_PATH =
            "data/stocks.csv";

    private StockFileReader() {
    }

    public static List<Stock> loadAllStocks() {

        List<Stock> stocks =
                new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(FILE_PATH))) {

            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] parts =
                        line.split(",");

                if (parts.length < 3) {
                    continue;
                }

                String theme =
                        parts[0].trim();

                String name =
                        parts[1].trim();

                String symbol =
                        parts[2].trim();

                stocks.add(
                        new Stock(

                                name,
                                //api호출
                                symbol,
                                //
                                theme
                        )
                );
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "stocks.csv 읽기 실패",
                    e
            );
        }

        return stocks;
    }
}