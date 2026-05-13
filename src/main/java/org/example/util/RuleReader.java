package org.example.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class RuleReader {

    private static final Random random = new Random();

    public static void readRules() {

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader("data/rules.json"))) {

            String line;

            while ((line = br.readLine()) != null) {

                System.out.println(line);
            }

        } catch (IOException e) {

            System.out.println("rules.json 읽기 실패");
        }
    }

    public static String getRandomFoodByCondition(
            String condition) {

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader("data/rules.json"))) {

            String line;
            boolean foundCondition = false;

            while ((line = br.readLine()) != null) {

                if (line.contains("\"condition\": \"" + condition + "\"")) {
                    foundCondition = true;
                }

                if (foundCondition && line.contains("\"foods\"")) {

                    int start = line.indexOf("[") + 1;
                    int end = line.indexOf("]");

                    String foodsText =
                            line.substring(start, end);

                    String[] foods =
                            foodsText.replace("\"", "")
                                    .split(",");

                    int index =
                            random.nextInt(foods.length);

                    return foods[index].trim();
                }
            }

        } catch (IOException e) {
            System.out.println("음식 추천 실패");
        }

        return "추천 음식 없음";
    }
}