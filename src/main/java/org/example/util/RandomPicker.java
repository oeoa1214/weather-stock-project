package org.example.util;

import java.util.List;
import java.util.Random;

public class RandomPicker {

    private static final Random random =
            new Random();

    public static String pick(
            List<String> items
    ) {

        int index =
                random.nextInt(items.size());

        return items.get(index);
    }
}