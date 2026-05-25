package org.example.service;

public class Stem3 {

    private Delivery3 delivery3;

    public void receiveFromDelivery3(
            Delivery3 delivery3
    ) {
        if (delivery3 == null) {
            throw new IllegalArgumentException(
                    "Stem3가 받은 Delivery3는 null일 수 없습니다."
            );
        }

        this.delivery3 =
                delivery3;
    }

    public Delivery3 sendToRun3() {
        if (delivery3 == null) {
            throw new IllegalStateException(
                    "Stem3에 run3으로 보낼 Delivery3가 없습니다."
            );
        }

        return delivery3;
    }

    public Delivery3 sendToStem4() {
        if (delivery3 == null) {
            throw new IllegalStateException(
                    "Stem3에 Stem4로 보낼 Delivery3가 없습니다."
            );
        }

        return delivery3;
    }
}