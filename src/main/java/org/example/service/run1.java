package org.example.service;

import org.example.model.Class1;
import org.example.model.weatherMom;

public class run1 {

    public Class1 createClass1(
            Stem2 stem2,
            weatherMom condition
    ) {
        if (stem2 == null) {
            throw new IllegalArgumentException(
                    "run1이 받은 Stem2는 null일 수 없습니다."
            );
        }

        if (condition == null) {
            throw new IllegalArgumentException(
                    "run1이 받은 날씨 조건은 null일 수 없습니다."
            );
        }

        return new Class1(
                stem2.sendTemperatureToRun1(),
                stem2.sendPrecipitationToRun1(),
                stem2.sendWindspeedToRun1(),
                stem2.sendPm10ToRun1(),
                stem2.sendHumidityToRun1(),
                stem2.sendWeathercodeToRun1(),
                condition
        );
    }
}