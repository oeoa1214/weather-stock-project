package org.example.model;
import java.util.Random;
public class SunnyCondition extends WeatherCondition {

    public SunnyCondition() {
        super("맑음", 1);
    }

    @Override
    public String getThemeName() {
        return "여행/소비";
    }

    @Override
    public String getFoodRecommendation() {
        String[] foods = {"김밥", "샌드위치", "브런치", "도시락", "샐러드"};
        Random random = new Random();
        return foods[random.nextInt(foods.length)];
    }
}