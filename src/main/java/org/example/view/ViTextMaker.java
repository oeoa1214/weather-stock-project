package org.example.view;

public class ViTextMaker {

    public String makeStockText(
            int number,
            String stockName,
            double currentPrice,
            double changeRate
    ) {
        return number
                + ". "
                + stockName
                + " 현재가 : "
                + String.format("%,.0f원", currentPrice)
                + " 변동율 : "
                + String.format("%+.2f", changeRate)
                + "%";
    }

    public String makeReadyText() {
        return "VI 대기 중";
    }

    public String makeWaitingText(
            int remainSeconds
    ) {
        return "변동성 완화 장치 발동 "
                + remainSeconds
                + "초후 해제";
    }

    public String makeOpenedText() {
        return "VI 해제";
    }

    public String makeStoppedText() {
        return "VI 정지";
    }
}