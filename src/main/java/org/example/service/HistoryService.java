package org.example.service;

import java.io.FileWriter;
import java.io.IOException;

public class HistoryService {

    private static final String FILE_PATH =
            "data/history.csv";

    public void saveHistory(
            String weather,
            String theme,
            String food,
            String bestTheme,
            double bestReturn) {

        try (FileWriter fw =
                     new FileWriter(FILE_PATH, true)) {

            fw.write(
                    weather + ","
                            + theme + ","
                            + food + ","
                            + bestTheme + ","
                            + bestReturn
                            + "\n"
            );

        } catch (IOException e) {

            System.out.println("history 저장 실패");
        }
    }
}