package org.example.controller;

import org.example.model.AnalysisProgressData;
import org.example.model.themeMom;
import org.example.model.weatherMom;
import org.example.service.Judge2;
import org.example.service.KospiWeatherCsvReader;
import org.example.service.KospiWeatherDay;
import org.example.service.WeatherThemeConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KospiWeatherAverageController {

    private final KospiWeatherCsvReader reader =
            new KospiWeatherCsvReader();

    private final Judge2 judge2 =
            new Judge2();

    private final WeatherThemeConverter converter =
            new WeatherThemeConverter();

    /*
     * 6번용:
     * 테마별 누적 합계
     */
    private final Map<String, Double> sumMap =
            new LinkedHashMap<>();

    /*
     * 7번 계산용:
     * 테마별 발생 횟수
     */
    private final Map<String, Integer> themeCountMap =
            new LinkedHashMap<>();

    /*
     * 8번 표시용:
     * 날씨별 발생 횟수
     */
    private final Map<String, Integer> weatherCountMap =
            new LinkedHashMap<>();

    /*
     * 5번용:
     * 오늘 하루 KOSPI 수익률을 오늘 테마에만 넣은 Map
     */
    private Map<String, Double> lastDailyThemeReturnMap =
            new LinkedHashMap<>();

    private List<KospiWeatherDay> days =
            List.of();

    private int index =
            0;

    private String currentDate =
            "";

    private String currentWeatherName =
            "";

    private String currentThemeName =
            "";

    private String colorWeatherName =
            "맑음";

    private double currentAverageTemperature =
            0.0;

    private double currentPrecipitation =
            0.0;

    private double currentPm10 =
            0.0;

    public void start(
            String filePath
    ) {
        days =
                reader.read(
                        filePath
                );

        index =
                0;

        currentDate =
                "";

        currentWeatherName =
                "";

        currentThemeName =
                "";

        colorWeatherName =
                "맑음";

        currentAverageTemperature =
                0.0;

        currentPrecipitation =
                0.0;

        currentPm10 =
                0.0;

        resetMaps();

        resetLastDailyThemeReturnMap();
    }

    private void resetMaps() {
        sumMap.clear();
        themeCountMap.clear();
        weatherCountMap.clear();

        sumMap.put("편의점·간편식", 0.0);
        sumMap.put("여행·소비", 0.0);
        sumMap.put("공기청정·위생", 0.0);
        sumMap.put("냉방·여름소비", 0.0);
        sumMap.put("난방·겨울소비", 0.0);

        themeCountMap.put("편의점·간편식", 0);
        themeCountMap.put("여행·소비", 0);
        themeCountMap.put("공기청정·위생", 0);
        themeCountMap.put("냉방·여름소비", 0);
        themeCountMap.put("난방·겨울소비", 0);

        weatherCountMap.put("맑음", 0);
        weatherCountMap.put("비", 0);
        weatherCountMap.put("미세먼지", 0);
        weatherCountMap.put("폭염", 0);
        weatherCountMap.put("한파", 0);
    }

    private void resetLastDailyThemeReturnMap() {
        lastDailyThemeReturnMap =
                new LinkedHashMap<>();

        lastDailyThemeReturnMap.put("편의점·간편식", 0.0);
        lastDailyThemeReturnMap.put("여행·소비", 0.0);
        lastDailyThemeReturnMap.put("공기청정·위생", 0.0);
        lastDailyThemeReturnMap.put("냉방·여름소비", 0.0);
        lastDailyThemeReturnMap.put("난방·겨울소비", 0.0);
    }

    public boolean isFinished() {
        return index >= days.size();
    }

    public int getCurrentDay() {
        return index;
    }

    public int getTotalDay() {
        return days.size();
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public Map<String, Integer> getWeatherCountMap() {
        return new LinkedHashMap<>(
                weatherCountMap
        );
    }

    public Map<String, Integer> getThemeCountMap() {
        return new LinkedHashMap<>(
                themeCountMap
        );
    }

    /*
     * 5번 패널용:
     * 오늘 하루치 테마 수익률 Map 반환
     */
    public Map<String, Double> getLastDailyThemeReturnMap() {
        return new LinkedHashMap<>(
                lastDailyThemeReturnMap
        );
    }

    public AnalysisProgressData getProgressData() {
        return new AnalysisProgressData(
                index,
                days.size(),
                currentDate,
                currentWeatherName,
                colorWeatherName,
                currentAverageTemperature,
                currentPrecipitation,
                currentPm10,
                new LinkedHashMap<>(
                        weatherCountMap
                ),
                new LinkedHashMap<>(
                        themeCountMap
                )
        );
    }

    /*
     * 반환값:
     * 6번용 누적 평균 Map
     */
    public Map<String, Double> nextDay() {
        if (days.isEmpty()) {
            throw new IllegalStateException(
                    "KOSPI 날씨 분석 모드가 시작되지 않았습니다. start()를 먼저 호출해야 합니다."
            );
        }

        if (index >= days.size()) {
            resetLastDailyThemeReturnMap();

            return createAverageMap();
        }

        KospiWeatherDay day =
                days.get(
                        index
                );

        currentDate =
                day.date();

        currentAverageTemperature =
                day.averageTemperature();

        currentPrecipitation =
                day.precipitation();

        currentPm10 =
                day.pm10();

        weatherMom judgedWeather =
                judge2.judge(
                        day.averageTemperature(),
                        day.precipitation(),
                        day.pm10()
                );

        currentWeatherName =
                judgedWeather.getName();

        colorWeatherName =
                currentWeatherName;

        themeMom theme =
                converter.convert(
                        judgedWeather
                );

        currentThemeName =
                theme.getThemeName();

        if (!sumMap.containsKey(currentThemeName)) {
            throw new IllegalArgumentException(
                    "등록되지 않은 테마입니다: "
                            + currentThemeName
            );
        }

        double currentSum =
                sumMap.get(
                        currentThemeName
                );

        int currentThemeCount =
                themeCountMap.get(
                        currentThemeName
                );

        int currentWeatherCount =
                weatherCountMap.getOrDefault(
                        currentWeatherName,
                        0
                );

        /*
         * 6번용 누적 평균 계산 재료
         */
        sumMap.put(
                currentThemeName,
                currentSum + day.kospiReturn()
        );

        themeCountMap.put(
                currentThemeName,
                currentThemeCount + 1
        );

        /*
         * 8번용 날씨 카운트
         */
        weatherCountMap.put(
                currentWeatherName,
                currentWeatherCount + 1
        );

        /*
         * 5번용 하루치 Map
         * 오늘 해당 테마에만 오늘 KOSPI 수익률을 넣는다.
         */
        resetLastDailyThemeReturnMap();

        lastDailyThemeReturnMap.put(
                currentThemeName,
                day.kospiReturn()
        );

        index++;

        return createAverageMap();
    }

    private Map<String, Double> createAverageMap() {
        Map<String, Double> averageMap =
                new LinkedHashMap<>();

        for (String themeName : sumMap.keySet()) {
            double sum =
                    sumMap.get(
                            themeName
                    );

            int count =
                    themeCountMap.get(
                            themeName
                    );

            double average =
                    0.0;

            if (count > 0) {
                average =
                        sum / count;
            }

            averageMap.put(
                    themeName,
                    average
            );
        }

        return averageMap;
    }
}