package org.example.service;

import org.example.model.Class9;

public class run9 {

    private final FoodInfoService foodInfoService =
            new FoodInfoService();

    public Class9 createClass9(
            String weatherName
    ) {
        if (weatherName == null || weatherName.isBlank()) {
            return foodInfoService.createClass9(
                    "맑음"
            );
        }

        return foodInfoService.createClass9(
                weatherName
        );
    }

    public Class9 run(
            String weatherName
    ) {
        return createClass9(
                weatherName
        );
    }

    public Class9 createFoodInfo(
            String weatherName
    ) {
        return createClass9(
                weatherName
        );
    }
}