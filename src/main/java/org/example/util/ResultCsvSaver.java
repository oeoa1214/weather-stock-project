package org.example.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public final class ResultCsvSaver {

    private static final Path RESULT_DIR =
            Path.of("data", "result");

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ResultCsvSaver() {
    }

    public static synchronized void savePanel1Weather(
            String weatherName,
            double temperature,
            int humidity,
            double precipitation,
            double windSpeed,
            double pm10
    ) {
        Path filePath =
                RESULT_DIR.resolve(
                        "panel1_weather_history.csv"
                );

        writeLine(
                filePath,
                "실행시간,날씨,기온,습도,강수량,풍속,미세먼지",
                csv(now()) + "," +
                        csv(weatherName) + "," +
                        temperature + "," +
                        humidity + "," +
                        precipitation + "," +
                        windSpeed + "," +
                        pm10
        );
    }

    public static synchronized void savePanel2Recommend(
            String themeName,
            String stockName,
            double currentPrice,
            double returnRate,
            String reason
    ) {
        Path filePath =
                RESULT_DIR.resolve(
                        "panel2_recommend_history.csv"
                );

        writeLine(
                filePath,
                "실행시간,테마,대표종목,현재가,수익률,추천이유",
                csv(now()) + "," +
                        csv(themeName) + "," +
                        csv(stockName) + "," +
                        currentPrice + "," +
                        returnRate + "," +
                        csv(reason)
        );
    }

    public static synchronized void savePanel9Food(
            String weatherName,
            String foodName,
            String reason
    ) {
        Path filePath =
                RESULT_DIR.resolve(
                        "panel9_food_history.csv"
                );

        writeLine(
                filePath,
                "실행시간,날씨,추천음식,추천이유",
                csv(now()) + "," +
                        csv(weatherName) + "," +
                        csv(foodName) + "," +
                        csv(reason)
        );
    }

    public static synchronized void saveFinalDashboardHistory(
            String weatherName,
            double temperature,
            double pm10,
            String themeName,
            String stockName,
            double currentPrice,
            double returnRate,
            String foodName
    ) {
        Path filePath =
                RESULT_DIR.resolve(
                        "final_dashboard_history.csv"
                );

        writeLine(
                filePath,
                "실행시간,날씨,기온,미세먼지,선택테마,대표종목,현재가,수익률,추천음식",
                csv(now()) + "," +
                        csv(weatherName) + "," +
                        temperature + "," +
                        pm10 + "," +
                        csv(themeName) + "," +
                        csv(stockName) + "," +
                        currentPrice + "," +
                        returnRate + "," +
                        csv(foodName)
        );
    }

    public static synchronized void savePanel6And8AnalysisResult(
            String modeName,
            Map<String, Double> panel6RateMap,
            Map<String, Integer> panel8CountMap
    ) {
        Path filePath =
                RESULT_DIR.resolve(
                        "analysis_result_history.csv"
                );

        createResultDirectory();

        boolean isNewFile =
                Files.notExists(
                        filePath
                );

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             filePath,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND
                     )) {

            if (isNewFile) {
                writer.write(
                        "실행시간,분석모드,구분,발생횟수,평균수익률"
                );
                writer.newLine();
            }

            String currentTime =
                    now();

            for (Map.Entry<String, Double> entry : panel6RateMap.entrySet()) {
                String key =
                        entry.getKey();

                int count =
                        panel8CountMap.getOrDefault(
                                key,
                                0
                        );

                writer.write(
                        csv(currentTime) + "," +
                                csv(modeName) + "," +
                                csv(key) + "," +
                                count + "," +
                                entry.getValue()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println(
                    "6번/8번 분석 결과 저장 실패: " + e.getMessage()
            );
        }
    }

    private static void writeLine(
            Path filePath,
            String header,
            String line
    ) {
        createResultDirectory();

        boolean isNewFile =
                Files.notExists(
                        filePath
                );

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             filePath,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND
                     )) {

            if (isNewFile) {
                writer.write(
                        header
                );
                writer.newLine();
            }

            writer.write(
                    line
            );
            writer.newLine();

        } catch (IOException e) {
            System.out.println(
                    "CSV 저장 실패: " + e.getMessage()
            );
        }
    }


    public static synchronized void saveRefreshDashboardHistory(
            String weatherName,
            String themeName,
            String stockName,
            double currentPrice,
            double returnRate,
            String foodName
    ) {
        Path filePath =
                RESULT_DIR.resolve(
                        "refresh_dashboard_history.csv"
                );

        writeLine(
                filePath,
                "실행시간,갱신기준날씨,갱신테마,대표종목,현재가,수익률,추천음식",
                csv(now()) + "," +
                        csv(weatherName) + "," +
                        csv(themeName) + "," +
                        csv(stockName) + "," +
                        currentPrice + "," +
                        returnRate + "," +
                        csv(foodName)
        );
    }





    private static void createResultDirectory() {
        try {
            Files.createDirectories(
                    RESULT_DIR
            );
        } catch (IOException e) {
            System.out.println(
                    "result 폴더 생성 실패: " + e.getMessage()
            );
        }
    }

    private static String now() {
        return LocalDateTime.now().format(
                FORMATTER
        );
    }

    private static String csv(
            String value
    ) {
        if (value == null) {
            return "";
        }

        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}