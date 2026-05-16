package org.example.model;

public final class Stock {

    private final String name;
    private final String symbol;
    private final String theme;

    public Stock(
            String name,
            String symbol,
            String theme
    ) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "종목명은 비어 있을 수 없습니다."
            );
        }

        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException(
                    "심볼은 비어 있을 수 없습니다."
            );
        }

        if (theme == null || theme.isBlank()) {
            throw new IllegalArgumentException(
                    "테마는 비어 있을 수 없습니다."
            );
        }

        this.name = name;
        this.symbol = symbol;
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTheme() {
        return theme;
    }
}