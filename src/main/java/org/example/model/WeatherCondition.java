package org.example.model;

public abstract class WeatherCondition {
    protected String name;
    protected int priority;

    public WeatherCondition(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public abstract String getThemeName();

    public abstract String getFoodRecommendation();
}

