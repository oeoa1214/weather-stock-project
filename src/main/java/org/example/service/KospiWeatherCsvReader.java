package org.example.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class KospiWeatherCsvReader {

    public List<KospiWeatherDay> read(
            String filePath
    ) {
        List<KospiWeatherDay> result =
                new ArrayList<>();

        try (
                BufferedReader br =
                        new BufferedReader(
                                new FileReader(filePath)
                        )
        ) {

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] parts =
                        line.split(",");

                result.add(
                        new KospiWeatherDay(
                                parts[0].trim(),
                                Double.parseDouble(parts[1].trim()),
                                Double.parseDouble(parts[2].trim()),
                                Double.parseDouble(parts[3].trim()),
                                Double.parseDouble(parts[4].trim())
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "KOSPI 날씨 CSV 읽기 실패",
                    e
            );
        }

        return result;
    }
}