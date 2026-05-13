package org.example.model;

import java.util.Random;

public class ColdWaveCondition extends WeatherCondition {

    public ColdWaveCondition() {
        super("한파", 3);
    }

    @Override
    public String getThemeName() {
        return "난방/에너지";
    }

    @Override
    public String getFoodRecommendation() {
        String[] foods = {"국밥", "부대찌개", "전골", "어묵탕", "칼국수"};
        Random random = new Random();
        return foods[random.nextInt(foods.length)];
    }
}