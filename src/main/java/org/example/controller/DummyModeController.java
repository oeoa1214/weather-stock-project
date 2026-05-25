package org.example.controller;

import org.example.model.themeMom;
import org.example.model.weatherMom;
import org.example.service.DaeguWeatherCsvReader;
import org.example.service.DaeguWeatherDay;
import org.example.service.DailyThemeReturnGenerator;
import org.example.service.Judge2;
import org.example.service.WeatherThemeConverter;

import java.util.List;
import java.util.Map;

public class DummyModeController {

    /*
     * 1. 파일 읽는 애
     * data/gumi_weather.csv 또는 data/seoul_weather.csv를 읽는다.
     */
    private final DaeguWeatherCsvReader reader =
            new DaeguWeatherCsvReader();

    /*
     * 2. 날씨 판단 애
     * 평균기온, 강수량, 미세먼지를 보고
     * 맑음 / 비 / 폭염 / 한파 / 미세먼지를 판정한다.
     */
    private final Judge2 judge2 =
            new Judge2();

    /*
     * 3. 날씨 → 주식 테마 변환 애
     * 비 → 편의점·간편식
     * 맑음 → 여행·소비
     * 미세먼지 → 공기청정·위생
     * 폭염 → 냉방·여름소비
     * 한파 → 난방·겨울소비
     */
    private final WeatherThemeConverter converter =
            new WeatherThemeConverter();

    /*
     * 4. 하루 테마 평균 수익률 생성 애
     * 오늘 적중 테마와 보정치를 받아
     * 5개 테마의 하루 수익률 Map을 만든다.
     */
    private final DailyThemeReturnGenerator generator =
            new DailyThemeReturnGenerator();

    /*
     * 5. CSV에서 읽은 365일 날씨 데이터
     */
    private List<DaeguWeatherDay> days =
            List.of();

    /*
     * 6. 현재 몇 번째 날짜까지 진행했는지
     */
    private int index =
            0;

    /*
     * 기본 시작.
     * 지역 선택 없이 호출되면 구미 파일을 기본으로 사용한다.
     */
    public void start() {
        start(
                "data/daegu_weather.csv"

        );
    }

    /*
     * 지역별 파일 시작.
     * 예:
     * start("data/gumi_weather.csv")
     * start("data/seoul_weather.csv")
     */
    public void start(
            String filePath
    ) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException(
                    "날씨 CSV 파일 경로는 비어 있을 수 없습니다."
            );
        }

        days =
                reader.read(
                        filePath
                );

        index =
                0;
    }

    /*
     * 365일을 모두 처리했는지 확인
     */
    public boolean isFinished() {
        return index >= days.size();
    }

    /*
     * 현재 전체 날짜 수 반환
     */
    public int getTotalDay() {
        return days.size();
    }

    /*
     * 현재 진행 인덱스 반환
     */
    public int getCurrentDay() {
        return index;
    }

    /*
     * 0.5초마다 하루씩 실행할 메서드.
     *
     * bonusLevel:
     * 0 → 보정 없음
     * 1 → 적중 테마 0~1% 보정
     * 2 → 적중 테마 0~2% 보정
     * 3 → 적중 테마 0~3% 보정
     */
    public DummyInput nextDay(
            int bonusLevel
    ) {
        if (days.isEmpty()) {
            start();
        }

        if (isFinished()) {
            throw new IllegalStateException(
                    "더미모드 365일이 모두 끝났습니다."
            );
        }

        /*
         * 1. 오늘 하루 날씨 데이터 꺼내기
         */
        DaeguWeatherDay todayWeatherData =
                days.get(
                        index
                );

        /*
         * 2. Judge2가 날씨 판단
         */
        weatherMom judgedWeather =
                judge2.judge(
                        todayWeatherData.averageTemperature(),
                        todayWeatherData.precipitation(),
                        todayWeatherData.pm10()
                );

        /*
         * 3. 날씨 → 5개 주식 테마 중 하나로 변환
         */
        themeMom todayTheme =
                converter.convert(
                        judgedWeather
                );

        /*
         * 4. 오늘 하루 5개 테마 평균 수익률 생성
         */
        Map<String, Double> dailyThemeAverageReturns =
                generator.createTodayReturns(
                        todayTheme.getThemeName(),
                        bonusLevel
                );

        /*
         * 5. 다음 날짜로 이동
         */
        index++;

        /*
         * 6. AppController가 받을 더미 입력값 반환
   .
         */
        return new DummyInput(
                index,
                days.size(),
                judgedWeather.getName(),
                todayTheme.getThemeName(),
                dailyThemeAverageReturns
        );
    }

    /*
     * AppController가 받을 더미모드 하루 입력값.
     *
     * currentDay:
     * 현재 진행일
     *
     * totalDay:
     * 전체 날짜 수
     *
     * weatherName:
     * Judge2가 판정한 오늘 날씨
     *
     * themeName:
     * 날씨에 대응된 오늘 주식 테마
     *
     * dailyThemeAverageReturns:
     * 오늘 하루 5개 테마 평균 수익률
     */
    public record DummyInput(
            int currentDay,
            int totalDay,
            String weatherName,
            String themeName,
            Map<String, Double> dailyThemeAverageReturns
    ) {
    }
}