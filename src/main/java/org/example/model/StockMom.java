package org.example.model;

public abstract class StockMom implements StockMomProvider {

    private final String themeName;

    protected StockMom(
            String themeName
    ) {
        if (themeName == null || themeName.isBlank()) {
            throw new IllegalArgumentException(
                    "themeName은 비어 있을 수 없습니다."
            );
        }

        this.themeName =
                themeName;
    }

    @Override
    public String themeName() {
        return themeName;
    }
}