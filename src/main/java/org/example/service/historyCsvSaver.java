package org.example.service;

import java.io.FileWriter;
import java.io.IOException;

public class historyCsvSaver {

    private static final String FILE_PATH =
            "data/history.csv";

    private static boolean headerWritten = false;

    public void saveHistory(
            String weather,
            String theme,
            String food,
            String bestTheme,
            double bestReturn
    ) {

        try (FileWriter fw =
                     new FileWriter(FILE_PATH, true)) {

            if (!headerWritten) {

                fw.write(
                        "weather,theme,food,bestTheme,bestReturn\n"
                );

                headerWritten = true;
            }

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