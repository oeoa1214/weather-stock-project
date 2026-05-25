package org.example.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DaeguWeatherCsvReader {

    /*
     * 기본값:
     * 기존 코드가 read()만 호출해도 대구/구미 파일을 읽도록 유지
     */
    private static final String DEFAULT_FILE_PATH =
            "data/gumi_weather.csv";

    /*
     * 기존 방식 유지용
     * read()를 호출하면 기본 파일을 읽는다.
     */
    public List<DaeguWeatherDay> read() {
        return read(
                DEFAULT_FILE_PATH
        );
    }

    /*
     * 새 방식
     * 서울/구미 등 원하는 파일 경로를 받아서 읽는다.
     */
    public List<DaeguWeatherDay> read(
            String filePath
    ) {
        List<DaeguWeatherDay> result =
                new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(filePath)
                     )) {

            br.readLine(); // 첫 줄 헤더 버림: 평균기온,강수량,미세먼지

            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] parts =
                        line.split(",");

                if (parts.length < 3) {
                    throw new IllegalArgumentException(
                            "CSV 형식이 잘못되었습니다: " + line
                    );
                }

                double averageTemperature =
                        Double.parseDouble(
                                parts[0].trim()
                        );

                double precipitation =
                        Double.parseDouble(
                                parts[1].trim()
                        );

                double pm10 =
                        Double.parseDouble(
                                parts[2].trim()
                        );

                result.add(
                        new DaeguWeatherDay(
                                averageTemperature,
                                precipitation,
                                pm10
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "날씨 CSV 읽기 실패: " + filePath,
                    e
            );
        }

        if (result.isEmpty()) {
            throw new IllegalStateException(
                    "날씨 CSV에 읽을 데이터가 없습니다: " + filePath
            );
        }

        return result;
    }
}