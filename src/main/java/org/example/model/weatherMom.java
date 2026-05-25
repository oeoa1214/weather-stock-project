package org.example.model;

public abstract class weatherMom {

    private final String name;
    private final int priority;

    public weatherMom(
            String name,
            int priority
    ) {
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