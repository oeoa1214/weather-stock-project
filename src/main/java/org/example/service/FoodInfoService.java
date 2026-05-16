package org.example.service;
import org.example.model.FoodInfo;

import java.util.List;
import java.util.Random;

public class FoodInfoService {

    private final Random random =
            new Random();

    public FoodInfo createFoodInfo(String weatherName) {

        if (weatherName.equals("맑음")) {
            return createSunnyFood();
        }

        if (weatherName.equals("비")) {
            return createRainFood();
        }

        if (weatherName.equals("폭염")) {
            return createHeatWaveFood();
        }

        if (weatherName.equals("한파")) {
            return createColdWaveFood();
        }

        if (weatherName.equals("미세먼지")) {
            return createDustFood();
        }

        return new FoodInfo(
                weatherName,
                "추천 음식",
                List.of("추천"),
                "오늘 날씨에 어울리는 음식입니다.",
                List.of("날씨 조건에 맞춘 추천입니다.")
        );
    }

    private FoodInfo createSunnyFood() {

        List<String> foods =
                List.of("샌드위치", "도시락", "김밥", "피크닉 음식", "브런치");

        String food =
                foods.get(random.nextInt(foods.size()));

        return new FoodInfo(
                "맑음",
                food,
                List.of("외출", "소비 증가", "가벼운 식사"),
                "맑은 날에는 외출과 야외 활동이 늘어나 간편하게 먹기 좋은 음식이 어울립니다.",
                List.of(
                        "야외 활동에 적합",
                        "이동 중 먹기 편함",
                        "가벼운 식사로 부담이 적음"
                )
        );
    }

    private FoodInfo createRainFood() {

        List<String> foods =
                List.of("김치칼국수", "파전", "국밥", "우동", "라멘");

        String food =
                foods.get(random.nextInt(foods.size()));

        return new FoodInfo(
                "비",
                food,
                List.of("따뜻한 국물", "실내 소비", "면 요리"),
                "비 오는 날에는 실내 활동이 늘어나고 따뜻한 국물 음식 선호가 높아집니다.",
                List.of(
                        "체온 유지에 도움",
                        "실내 식사에 적합",
                        "비 오는 날 선호도 높음"
                )
        );
    }

    private FoodInfo createHeatWaveFood() {

        List<String> foods =
                List.of("냉면", "콩국수", "빙수", "샐러드", "아이스커피");

        String food =
                foods.get(random.nextInt(foods.size()));

        return new FoodInfo(
                "폭염",
                food,
                List.of("시원한 음식", "수분 보충", "더위 완화"),
                "더운 날에는 체온을 낮추고 수분 보충에 도움이 되는 음식이 어울립니다.",
                List.of(
                        "더위 완화에 도움",
                        "수분 보충에 적합",
                        "가볍게 먹기 좋음"
                )
        );
    }

    private FoodInfo createColdWaveFood() {

        List<String> foods =
                List.of("국밥", "부대찌개", "전골", "어묵탕", "칼국수");

        String food =
                foods.get(random.nextInt(foods.size()));

        return new FoodInfo(
                "한파",
                food,
                List.of("따뜻한 음식", "난방", "에너지 보충"),
                "추운 날에는 몸을 따뜻하게 해주는 든든한 국물 음식이 어울립니다.",
                List.of(
                        "체온 유지에 도움",
                        "든든한 식사 가능",
                        "추운 날 선호도 높음"
                )
        );
    }

    private FoodInfo createDustFood() {

        List<String> foods =
                List.of("삼겹살", "도라지차", "배숙", "따뜻한 국물", "해장국");

        String food =
                foods.get(random.nextInt(foods.size()));

        return new FoodInfo(
                "미세먼지",
                food,
                List.of("실내 위생", "목 관리", "따뜻한 음식"),
                "미세먼지가 있는 날에는 목 관리와 실내 식사에 어울리는 음식이 좋습니다.",
                List.of(
                        "목 관리에 도움",
                        "실내 식사에 적합",
                        "따뜻하게 먹기 좋음"
                )
        );
    }
}