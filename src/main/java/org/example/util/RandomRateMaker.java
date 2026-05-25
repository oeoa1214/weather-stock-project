package org.example.util;

import java.util.Random;

public class RandomRateMaker {

    private static final Random random =
            new Random();

    private RandomRateMaker() {
    }

    public static double between(
            double min,
            double max
    ) {
        return min + random.nextDouble() * (max - min);
    }
}