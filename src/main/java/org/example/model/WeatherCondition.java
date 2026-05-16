package org.example.model;

public abstract class WeatherCondition {

    private final String name;
    private final int priority;

    public WeatherCondition(
            String name,
            int priority) {

        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
}