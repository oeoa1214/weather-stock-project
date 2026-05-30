package org.example.service;

import org.example.model.AirCareTheme;
import org.example.model.Class3;
import org.example.model.Class4;
import org.example.model.Class9;
import org.example.model.ColdWaveNow;
import org.example.model.CoolingTheme;
import org.example.model.DeliveryTheme;
import org.example.model.DustNow;
import org.example.model.HeatWaveNow;
import org.example.model.HeatingTheme;
import org.example.model.RainNow;
import org.example.model.RecommendRefreshResult;
import org.example.model.SunnyNow;
import org.example.model.TravelTheme;
import org.example.model.ViewClass6;
import org.example.model.themeMom;
import org.example.model.weatherMom;

import java.util.List;

public class RecommendRefreshService {

    public RecommendRefreshResult createRecommendRefreshResult(
            ViewClass6 viewClass6,
            List<Hub3Data.Item> hub3Items
    ) {
        if (viewClass6 == null) {
            throw new IllegalArgumentException(
                    "ViewClass6 결과는 null일 수 없습니다."
            );
        }

        if (hub3Items == null || hub3Items.isEmpty()) {
            throw new IllegalArgumentException(
                    "hub3Items는 비어 있을 수 없습니다."
            );
        }

        weatherMom judgedWeather =
                createWeatherByName(
                        viewClass6.weatherName()
                );

        themeMom theme =
                createThemeByWeather(
                        judgedWeather
                );

        hub3 hub3 =
                new hub3();

        hub3.receiveThemeFromThemeMom(
                theme
        );

        hub3.receiveItems(
                hub3Items
        );

        Hub3Data hub3Data =
                hub3.createHub3Data();

        Judge3 judge3 =
                new Judge3();

        Delivery3 delivery3 =
                judge3.judge(
                        hub3Data
                );

        Stem3 stem3 =
                new Stem3();

        stem3.receiveFromDelivery3(
                delivery3
        );

        run3 run3 =
                new run3();

        List<Class3> class3Result =
                run3.createClass3List(
                        stem3.sendToRun3()
                );

        Stem4 stem4 =
                new Stem4();

        stem4.receiveFromStem3(
                stem3.sendToStem4()
        );

        Judge4 judge4 =
                new Judge4();

        Delivery4 delivery4 =
                judge4.judge(
                        stem4.sendToJudge4()
                );

        run4 run4 =
                new run4();

        Class4 class4 =
                run4.createClass4(
                        delivery4
                );

        run9 run9 =
                new run9();

        Class9 class9 =
                run9.createClass9(
                        judgedWeather.getName()
                );

        return new RecommendRefreshResult(
                class4,
                class3Result,
                class9
        );
    }

    private weatherMom createWeatherByName(
            String weatherName
    ) {
        if (weatherName == null || weatherName.isBlank()) {
            throw new IllegalArgumentException(
                    "날씨명은 비어 있을 수 없습니다."
            );
        }

        if (weatherName.equals("비")) {
            return new RainNow();
        }

        if (weatherName.equals("폭염")) {
            return new HeatWaveNow();
        }

        if (weatherName.equals("한파")) {
            return new ColdWaveNow();
        }

        if (weatherName.equals("미세먼지")) {
            return new DustNow();
        }

        return new SunnyNow();
    }

    private themeMom createThemeByWeather(
            weatherMom weather
    ) {
        if (weather instanceof RainNow) {
            return new DeliveryTheme();
        }

        if (weather instanceof SunnyNow) {
            return new TravelTheme();
        }

        if (weather instanceof HeatWaveNow) {
            return new CoolingTheme();
        }

        if (weather instanceof ColdWaveNow) {
            return new HeatingTheme();
        }

        return new AirCareTheme();
    }
}