package org.example.OP;
//1. addChanges()
//   → 0.5초마다 oneMinuteArray에 값 누적
//   → synchronized라서 누적 중 다른 synchronized 메서드 못 들어옴
//
//2. waitAndTakeChanges()
//   → opened가 false면 wait()
//   → wait()에 들어가면 락을 잠깐 내려놓고 기다림
//
//3. open()
//   → 1분 지나면 opened = true
//   → notifyAll()
//   → wait 중인 waitAndTakeChanges() 깨움
//
//4. waitAndTakeChanges()
//
//   → oneMinuteArray 복사
//   → oneMinuteArray 초기화
//   → View에 넘길 배열 return
//
//5. stop()
//   → 중간 정지 시 stopped = true
//   → notifyAll()
//   → 기다리던 스레드 종료
import java.util.Arrays;
import java.util.Random;

public class OPChangeSaver {

    private static final int STOCK_COUNT = 5;

    // 랜덤 생성기
    private final Random random =
            new Random();

    // 1분 동안 누적할 5개 변동값
    private final double[] oneMinuteArray =
            new double[STOCK_COUNT];

    // 공개 여부
    private boolean opened;

    // 정지 여부
    private boolean stopped;

    // 새 VI 시작 전 초기화
    public synchronized void reset() {
        Arrays.fill(
                oneMinuteArray,
                0.0
        );

        opened =
                false;

        stopped =
                false;
    }

    // 1분 전: 랜덤 변동값 5개 누적

    //동기화 대상!! oneMinuteArray에 쓰기
    public synchronized void addChanges(
            double[] currentPrices
    ) {
        checkFivePrices(
                currentPrices
        );

        if (opened || stopped) {
            return;
        }

        for (int i = 0; i < STOCK_COUNT; i++) {
            oneMinuteArray[i] +=
                    makeChange(
                            currentPrices[i]
                    );
        }
    }

    // 1분 후 공개 신호

    //opened 값 변경
    //notifyAll()
    public synchronized void open() {
        if (stopped) {
            return;
        }

        opened =
                true;

        notifyAll();
    }

    // 공개될 때까지 대기 후 누적값 꺼내기
    //동기화대상 oneMinuteArray 읽기 + 초기
    public synchronized double[] waitAndTakeChanges()
            throws InterruptedException {

        while (!opened && !stopped) {
            wait();
        }

        if (stopped) {
            throw new InterruptedException(
                    "VI stopped"
            );
        }

        double[] result =
                new double[STOCK_COUNT];

        for (int i = 0; i < STOCK_COUNT; i++) {
            result[i] =
                    oneMinuteArray[i];

            oneMinuteArray[i] =
                    0.0;
        }

        return result;
    }

    // 공개 후 바로 사용할 5개 변동값 생성
    public synchronized double[] makeChanges(
            double[] currentPrices
    ) {
        //배열 5개인지검사
        checkFivePrices(
                currentPrices);

        double[] result =
                new double[STOCK_COUNT];

        for (int i = 0; i < STOCK_COUNT; i++) {
            result[i] =
                    makeChange(
                            currentPrices[i]
                    );
        }

        return result;
    }

    // 정지
    public synchronized void stop() {
        stopped =
                true;

        notifyAll();
    }

    // 현재가 기준 -0.5% ~ +0.5% 랜덤 변동값
    private double makeChange(
            double currentPrice
    ) {
        double randomRate =
                -0.5 + random.nextDouble();

        return currentPrice * randomRate / 100.0;
    }

    // 현재가 배열은 반드시 5개
    private void checkFivePrices(
            double[] currentPrices
    ) {
        if (currentPrices == null || currentPrices.length != STOCK_COUNT) {
            throw new IllegalArgumentException(
                    "현재가 배열은 반드시 5개여야 합니다."
            );
        }
    }
}