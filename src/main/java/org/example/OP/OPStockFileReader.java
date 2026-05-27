package org.example.OP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OPStockFileReader {

    private static final String FILE_PATH =
            "data/vi_stocks.csv";

    private static final int PICK_COUNT =
            5;

    public List<OPStock> loadAllStocks() {
        List<OPStock> stocks =
                new ArrayList<>();

        try (
                BufferedReader br =
                        new BufferedReader(
                                new FileReader(FILE_PATH)
                        )
        ) {
            String line;
            boolean firstLine =
                    true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine =
                            false;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                String[] parts =
                        line.split(",");

                if (parts.length != 3) {
                    throw new IllegalArgumentException(
                            "CSV 형식이 잘못되었습니다: " + line
                    );
                }

                String stockName =
                        parts[0].trim();

                double currentPrice =
                        Double.parseDouble(
                                parts[1].trim()
                        );

                double changeRate =
                        Double.parseDouble(
                                parts[2].trim()
                        );

                stocks.add(
                        new OPStock(
                                stockName,
                                currentPrice,
                                changeRate
                        )
                );
            }
        } catch (IOException e) {
            throw new IllegalStateException(
                    "vi_stocks.csv 파일을 읽지 못했습니다.",
                    e
            );
        }

        return stocks;
    }

    public List<OPStock> pickRandomFiveStocks() {
        List<OPStock> allStocks =
                loadAllStocks();

        if (allStocks.size() < PICK_COUNT) {
            throw new IllegalStateException(
                    "랜덤으로 뽑을 VI 종목이 5개보다 적습니다."
            );
        }

        List<OPStock> copiedStocks =
                new ArrayList<>(
                        allStocks
                );

        Collections.shuffle(
                copiedStocks
        );

        return new ArrayList<>(
                copiedStocks.subList(
                        0,
                        PICK_COUNT
                )
        );
    }
}