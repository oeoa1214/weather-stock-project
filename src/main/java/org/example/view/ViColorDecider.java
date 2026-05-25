package org.example.view;

import java.awt.Color;

public class ViColorDecider {

    // 실제 증권사 앱에서 많이 쓰는 가독성 높고 세련된 색상
    private static final Color STOCK_RED = new Color(223, 62, 79);   // 차분하면서도 선명한 딥 레드
    private static final Color STOCK_BLUE = new Color(30, 115, 232); // 눈이 편안한 트러스트 블루
    private static final Color STOCK_BLACK = new Color(51, 51, 51);  // 완전 검은색보다 부드러운 다크 그레이

    public Color decide(double changeRate) {
        if (changeRate > 0) {
            return STOCK_RED;
        }

        if (changeRate < 0) {
            return STOCK_BLUE;
        }

        return STOCK_BLACK;
    }
}