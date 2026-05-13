package org.example.model;

import java.util.Random;

public class HeatWaveCondition extends WeatherCondition {

    public HeatWaveCondition() {
        super("폭염", 3);
    }

    @Override
    public String getThemeName() {
        return "냉방/전력";
    }

    @Override
    public String getFoodRecommendation() {
        String[] foods = {"냉면", "콩국수", "빙수", "샐러드", "아이스커피"};
        Random random = new Random();
        return foods[random.nextInt(foods.length)];
    }
}