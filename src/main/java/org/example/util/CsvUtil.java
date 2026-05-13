package org.example.util;

import org.example.model.Stock;
import org.example.model.ThemeStrategy;
import org.example.model.WeatherCondition;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CsvUtil {

    private static final String FILE_PATH = "history.csv";

    private static void ensureHeader() {
        try {
            java.io.File f = new java.io.File(FILE_PATH);

            if (!f.exists()) {
                FileWriter fw = new FileWriter(FILE_PATH, true);
                fw.write("date,weather,theme,stock,price,return\n");
                fw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveResult(WeatherCondition weather,
                                  ThemeStrategy theme,
                                  List<Stock> stocks) {

        ensureHeader();

        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            String date = LocalDate.now().toString();

            for (Stock s : stocks) {
                fw.write(String.format("%s,%s,%s,%s,%.0f,%.2f\n",
                        date,
                        weather.getName(),
                        theme.getThemeName(),
                        s.getName(),
                        s.getCurrentPrice(),
                        s.getDailyReturn()
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}