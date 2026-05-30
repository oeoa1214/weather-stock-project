package org.example.model;

public record ViewClass6(
        String weatherName,
        String themeName
) {
    public ViewClass6 {
        if (weatherName == null || weatherName.isBlank()) {
            throw new IllegalArgumentException(
                    "날씨명은 비어 있을 수 없습니다."
            );
        }

        if (themeName == null || themeName.isBlank()) {
            throw new IllegalArgumentException(
                    "테마명은 비어 있을 수 없습니다."
            );
        }
    }
}