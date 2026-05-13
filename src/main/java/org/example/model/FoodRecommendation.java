package org.example.model;

public class FoodRecommendation {
    private String weatherName;
    private String foodName;
    private String reason;

    public FoodRecommendation(String weatherName, String foodName, String reason) {
        this.weatherName = weatherName;
        this.foodName = foodName;
        this.reason = reason;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getReason() {
        return reason;
    }
}