package org.example.service;

import org.example.model.StockSnapshot;
import org.example.model.ThemePerformance;

import java.util.List;

public class StockPrinterService {

    public void printSnapshots(
            List<StockSnapshot> snapshots
    ) {

        System.out.println();
        System.out.println("=== 추천 종목 TOP 5 ===");

        for (StockSnapshot snapshot : snapshots) {

            System.out.println(
                    snapshot.getStock().getName()
                            + " | 현재가: "
                            + snapshot.getCurrentPrice()
                            + " | 7일 수익률: "
                            + String.format(
                            "%.2f%%",
                            snapshot.getReturnRate()
                    )
            );
        }
    }

    public void printThemePerformances(
            List<ThemePerformance> performances
    ) {

        System.out.println();
        System.out.println("=== 테마별 최근 7일 성과 ===");

        for (ThemePerformance performance : performances) {

            System.out.printf(
                    "%s : %.2f%%\n",
                    performance.themeName(),
                    performance.averageReturn()
            );
        }
    }

    public void printBestTheme(
            ThemePerformance bestTheme
    ) {

        System.out.println();
        System.out.println("=== 최근 7일 최고 성과 ===");

        System.out.println(
                bestTheme.themeName()
                        + " "
                        + String.format(
                        "%.2f%%",
                        bestTheme.averageReturn()
                )
        );

        System.out.println(
                "최고 종목 : "
                        + bestTheme.bestStock()
        );

        System.out.println(
                "종목 수익률 : "
                        + String.format(
                        "%.2f%%",
                        bestTheme.bestStockReturn()
                )
        );
    }
}