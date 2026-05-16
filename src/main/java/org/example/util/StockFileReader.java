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

    public static List<Stock> loadStocksByTheme(
            String targetTheme) {

        final List<Stock> stocks =
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

                final String[] parts =
                        line.split(",");

                if (parts.length < 3) {
                    continue;
                }

                final String theme =
                        parts[0].trim();

                final String name =
                        parts[1].trim();

                final String symbol =
                        parts[2].trim();

                if (!theme.equals(targetTheme)) {
                    continue;
                }

                stocks.add(
                        new Stock(
                                name,
                                symbol,
                                theme
                        )
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "stocks.csv 읽기 실패"
            );
        }

        return stocks;
    }
}