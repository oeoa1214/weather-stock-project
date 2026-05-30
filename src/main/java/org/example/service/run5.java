package org.example.service;

import org.example.model.Class5;
import org.example.model.CloseInfo;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class run5 {

    private static final int MAX_DAYS =
            7;

    private final Class5 class5 =
            new Class5();

    private final Map<String, Queue<Double>> dummySevenDayStack =
            new LinkedHashMap<>();

    public run5() {
        resetDummyStack();
    }

    /*
     * 기존 일반 경로
     * CloseInfo 안에 이미 7일 종가가 있으므로
     * 그 closePrices를 그대로 이용해서 5개 테마별 최근 7일 수익률 계산
     */
    public Class5 createClass5ByCloseInfo(
            List<CloseInfo> closeInfos
    ) {
        if (closeInfos == null || closeInfos.isEmpty()) {
            throw new IllegalArgumentException(
                    "run5에 전달된 CloseInfo 목록은 비어 있을 수 없습니다."
            );
        }

        class5.reset();

        Map<String, Double> sumByTheme =
                createEmptyDoubleMap();

        Map<String, Integer> countByTheme =
                createEmptyIntegerMap();

        for (CloseInfo closeInfo : closeInfos) {
            String themeName =
                    closeInfo.stock().getTheme();

            if (!sumByTheme.containsKey(themeName)) {
                continue;
            }

            double returnRate =
                    calculateReturnRateByClosePrices(
                            closeInfo.closePrices()
                    );

            sumByTheme.put(
                    themeName,
                    sumByTheme.get(themeName) + returnRate
            );

            countByTheme.put(
                    themeName,
                    countByTheme.get(themeName) + 1
            );
        }

        for (String themeName : sumByTheme.keySet()) {
            int count =
                    countByTheme.get(themeName);

            double averageReturn =
                    0.0;

            if (count > 0) {
                averageReturn =
                        sumByTheme.get(themeName) / count;
            }

            class5.setRate(
                    themeName,
                    averageReturn
            );
        }

        return class5;
    }

    /*
     * 더미 경로
     * todayReturns는 하루치 난수 수익률
     * 7일 넘으면 가장 오래된 값 제거하고 새 값 추가
     */
    public Class5 createClass5ByDummy(
            Map<String, Double> todayReturns
    ) {
        if (todayReturns == null || todayReturns.isEmpty()) {
            throw new IllegalArgumentException(
                    "todayReturns는 비어 있을 수 없습니다."
            );
        }

        for (String themeName : dummySevenDayStack.keySet()) {
            Double todayReturn =
                    todayReturns.get(themeName);

            if (todayReturn == null) {
                throw new IllegalArgumentException(
                        "더미 수익률에 없는 테마입니다: " + themeName
                );
            }

            Queue<Double> stack =
                    dummySevenDayStack.get(themeName);

            stack.add(
                    todayReturn
            );

            if (stack.size() > MAX_DAYS) {
                stack.poll();
            }

            double sevenDayReturn =
                    calculateReturnRateByDailyRates(
                            stack
                    );

            class5.setRate(
                    themeName,
                    sevenDayReturn
            );
        }

        return class5;
    }

    public Class5 resetDummy() {
        resetDummyStack();

        class5.reset();

        return class5;
    }

    public Class5 getClass5() {
        return class5;
    }

    private void resetDummyStack() {
        dummySevenDayStack.clear();

        dummySevenDayStack.put("편의점·간편식", new ArrayDeque<>());
        dummySevenDayStack.put("여행·소비", new ArrayDeque<>());
        dummySevenDayStack.put("공기청정·위생", new ArrayDeque<>());
        dummySevenDayStack.put("냉방·여름소비", new ArrayDeque<>());
        dummySevenDayStack.put("난방·겨울소비", new ArrayDeque<>());
    }

    private double calculateReturnRateByClosePrices(
            List<Double> closePrices
    ) {
        if (closePrices == null || closePrices.size() < 2) {
            return 0.0;
        }

        double first =
                closePrices.get(0);

        double last =
                closePrices.get(
                        closePrices.size() - 1
                );

        if (first == 0.0) {
            return 0.0;
        }

        return ((last - first) / first) * 100.0;
    }

    private double calculateReturnRateByDailyRates(
            Queue<Double> dailyRates
    ) {
        if (dailyRates == null || dailyRates.isEmpty()) {
            return 0.0;
        }

        double sum =
                0.0;

        for (double dailyRate : dailyRates) {
            sum +=
                    dailyRate;
        }

        return sum;
    }
    private Map<String, Double> createEmptyDoubleMap() {
        Map<String, Double> map =
                new LinkedHashMap<>();

        map.put("편의점·간편식", 0.0);
        map.put("여행·소비", 0.0);
        map.put("공기청정·위생", 0.0);
        map.put("냉방·여름소비", 0.0);
        map.put("난방·겨울소비", 0.0);

        return map;
    }

    private Map<String, Integer> createEmptyIntegerMap() {
        Map<String, Integer> map =
                new LinkedHashMap<>();

        map.put("편의점·간편식", 0);
        map.put("여행·소비", 0);
        map.put("공기청정·위생", 0);
        map.put("냉방·여름소비", 0);
        map.put("난방·겨울소비", 0);

        return map;
    }
}