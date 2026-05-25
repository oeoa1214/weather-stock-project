package org.example.service;

import org.example.model.Class6;
import org.example.model.CloseInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class run6 {

    private final Class6 class6 =
            new Class6();

    /*
     * 더미 경로 누적용
     * 하루 수익률을 계속 더해서 누적수익률처럼 저장
     */
    private final Map<String, Double> dummyCumulativeRates =
            new LinkedHashMap<>();

    public run6() {
        resetDummyRates();
    }

    /*
     * 기존 일반 경로
     * CloseInfo 안의 7일 종가를 이용해서
     * 현재 일반 누적수익률을 Class6에 덮어쓰기
     */
    public Class6 createClass6ByCloseInfo(
            List<CloseInfo> closeInfos
    ) {
        if (closeInfos == null || closeInfos.isEmpty()) {
            throw new IllegalArgumentException(
                    "run6에 전달된 CloseInfo 목록은 비어 있을 수 없습니다."
            );
        }

        class6.reset();

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

            class6.setRate(
                    themeName,
                    averageReturn
            );
        }

        return class6;
    }

    /*
     * 더미 경로
     * 더미 버튼 누른 뒤에는 이 메서드가 0.5초마다 호출됨
     * 같은 더미 흐름에서는 += 누적
     */
    public Class6 createClass6ByDummy(
            Map<String, Double> todayReturns
    ) {
        if (todayReturns == null || todayReturns.isEmpty()) {
            throw new IllegalArgumentException(
                    "todayReturns는 비어 있을 수 없습니다."
            );
        }

        for (String themeName : dummyCumulativeRates.keySet()) {
            Double todayReturn =
                    todayReturns.get(themeName);

            if (todayReturn == null) {
                throw new IllegalArgumentException(
                        "더미 수익률에 없는 테마입니다: " + themeName
                );
            }

            double before =
                    dummyCumulativeRates.get(themeName);

            double after =
                    before + todayReturn;

            dummyCumulativeRates.put(
                    themeName,
                    after
            );

            class6.setRate(
                    themeName,
                    after
            );
        }

        return class6;
    }

    public Class6 resetDummy() {
        resetDummyRates();

        class6.reset();

        return class6;
    }

    public Class6 getClass6() {
        return class6;
    }

    private void resetDummyRates() {
        dummyCumulativeRates.clear();

        dummyCumulativeRates.put("편의점·간편식", 0.0);
        dummyCumulativeRates.put("여행·소비", 0.0);
        dummyCumulativeRates.put("공기청정·위생", 0.0);
        dummyCumulativeRates.put("냉방·여름소비", 0.0);
        dummyCumulativeRates.put("난방·겨울소비", 0.0);
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