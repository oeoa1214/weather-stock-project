package org.example.model;

import java.util.Random;

public class DustCondition extends WeatherCondition {

    public DustCondition() {
        super("미세먼지", 4);
    }

    @Override
    public String getThemeName() {
        return "공기청정/위생";
    }

    @Override
    public String getFoodRecommendation() {
        String[] foods = {"삼겹살", "도라지차", "배숙", "따뜻한 국물", "해장국"};
        Random random = new Random();
        return foods[random.nextInt(foods.length)];
    }
}