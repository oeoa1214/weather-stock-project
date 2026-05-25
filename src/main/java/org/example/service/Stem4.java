package org.example.service;

public class Stem4 {

    private Delivery3 delivery3;

    public void receiveFromStem3(
            Delivery3 delivery3
    ) {
        if (delivery3 == null) {
            throw new IllegalArgumentException(
                    "Stem4가 받은 Delivery3는 null일 수 없습니다."
            );
        }

        this.delivery3 =
                delivery3;
    }

    public Delivery3 sendToJudge4() {
        if (delivery3 == null) {
            throw new IllegalStateException(
                    "Stem4에 Judge4로 보낼 Delivery3가 없습니다."
            );
        }

        return delivery3;
    }
}