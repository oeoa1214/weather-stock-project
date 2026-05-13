package org.example.model;
import java.util.Random;
public class RainCondition extends WeatherCondition {

    public RainCondition() {
        super("비", 2);
    }

    @Override
    public String getThemeName() {
        return "배달/온라인";
    }

    @Override
    public String getFoodRecommendation() {

            String[] foods = {"김치칼국수", "파전", "국밥", "우동", "라멘"};
            Random random = new Random();
            return foods[random.nextInt(foods.length)];
        }
    }
