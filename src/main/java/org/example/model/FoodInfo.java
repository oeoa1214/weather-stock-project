package org.example.model;

import java.util.List;

public record FoodInfo(
        String weatherName,
        String foodName,
        List<String> tags,
        String description,
        List<String> reasons
) {

    public FoodInfo {

        if (weatherName == null || weatherName.isBlank()) {
            throw new IllegalArgumentException(
                    "날씨 이름은 비어 있을 수 없습니다."
            );
        }

        if (foodName == null || foodName.isBlank()) {
            throw new IllegalArgumentException(
                    "음식 이름은 비어 있을 수 없습니다."
            );
        }

        if (tags == null) {
            tags = List.of();
        } else {
            tags = List.copyOf(tags);
        }

        if (description == null || description.isBlank()) {
            description = "오늘 날씨에 어울리는 추천 음식입니다.";
        }

        if (reasons == null) {
            reasons = List.of();
        } else {
            reasons = List.copyOf(reasons);
        }
    }
}