package org.example.view;

import org.example.vi.ViChangeSaver;
import org.example.vi.ViStock;
import org.example.vi.ViStockFileReader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViSimulationPanel extends JPanel {

    private static final int TICK_MS = 500;
    private static final int HIDDEN_WAIT_MS = 60000; // 다시 60초(60000ms)로 원복

    private final ViStockFileReader stockFileReader = new ViStockFileReader();
    private final ViChangeSaver viChangeSaver = new ViChangeSaver();
    private final ViTextMaker textMaker = new ViTextMaker();

    private final List<ViewStock> viewStocks = new ArrayList<>();
    private final List<ViStockRowPanel> rowPanels = new ArrayList<>();

    private final JLabel statusLabel = new JLabel();
    private final JButton pickButton = new JButton("종목 새로뽑기");
    private final JButton viButton = new JButton("VI 시작");

    private Thread producerThread;
    private Thread consumerThread;
    private volatile boolean running;

    public ViSimulationPanel() {
        setLayout(new BorderLayout(8, 8));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "7. VI 더미 주가 변동",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("맑은 고딕", Font.BOLD, 13),
                Color.DARK_GRAY
        ));

        setupStatusLabel();
        add(statusLabel, BorderLayout.NORTH);
        add(createRowsPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pickRandomStocks();
    }

    private void setupStatusLabel() {
        statusLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setText(textMaker.makeReadyText());
    }

    private JPanel createRowsPanel() {
        JPanel rowsPanel = new JPanel(new GridLayout(5, 1, 4, 4));
        rowsPanel.setBackground(Color.WHITE);

        for (int i = 0; i < 5; i++) {
            ViStockRowPanel rowPanel = new ViStockRowPanel();
            rowPanels.add(rowPanel);
            rowsPanel.add(rowPanel);
        }
        return rowsPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        buttonPanel.setBackground(Color.WHITE);

        setupButton(pickButton);
        setupButton(viButton);

        pickButton.addActionListener(event -> pickRandomStocks());
        viButton.addActionListener(event -> toggleVi());

        buttonPanel.add(pickButton);
        buttonPanel.add(viButton);

        return buttonPanel;
    }

    private void setupButton(JButton button) {
        button.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        button.setFocusPainted(false);
    }

    private void pickRandomStocks() {
        stopVi();

        List<ViStock> pickedStocks = stockFileReader.pickRandomFiveStocks();
        viewStocks.clear();

        for (ViStock stock : pickedStocks) {
            viewStocks.add(new ViewStock(stock.stockName(), stock.currentPrice()));
        }

        refreshRows();
        statusLabel.setText(textMaker.makeReadyText());
    }

    private void toggleVi() {
        if (running) {
            stopVi();
        } else {
            startVi();
        }
    }

    private void startVi() {
        if (viewStocks.size() != 5) return;

        running = true;
        viChangeSaver.reset();

        viButton.setText("VI 정지");
        statusLabel.setText(textMaker.makeWaitingText(60));

        // [생산자 스레드] 60초 동안 카운트다운 갱신 & 뒤에서 값 누적
        producerThread = new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();

                while (running) {
                    long elapsed = System.currentTimeMillis() - startTime;
                    if (elapsed >= HIDDEN_WAIT_MS) break; // 60초 지나면 탈출

                    // 남은 시간 계산해서 화면 갱신
                    int remainSeconds = 60 - (int) (elapsed / 1000);
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText(textMaker.makeWaitingText(remainSeconds));
                    });

                    Thread.sleep(TICK_MS); // 0.5초 대기
                    viChangeSaver.addChanges(getCurrentPrices()); // 0.5초마다 누적
                }

                // 60초가 꽉 차면 빗장을 품 (내부적으로 notifyAll 실행)
                if (running) {
                    viChangeSaver.open();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-Thread");

        // [소비자 스레드] wait()로 숨죽여 기다리다가 문 열리면 화면에 뿌리고 실시간 갱신
        consumerThread = new Thread(() -> {
            try {
                // 생산자가 open() 할 때까지 대기
                double[] oneMinuteChanges = viChangeSaver.waitAndTakeChanges();

                if (!running) return;

                // 잠에서 깨어나면 누적된 값 한 번에 화면에 적용
                SwingUtilities.invokeLater(() -> {
                    applyChangesToViewStocks(oneMinuteChanges);
                    statusLabel.setText(textMaker.makeOpenedText());
                    refreshRows();
                });

                // 이후 0.5초마다 실시간 갱신 무한 반복
                while (running && !Thread.currentThread().isInterrupted()) {
                    Thread.sleep(TICK_MS);
                    double[] liveChanges = viChangeSaver.makeChanges(getCurrentPrices());

                    SwingUtilities.invokeLater(() -> {
                        applyChangesToViewStocks(liveChanges);
                        refreshRows();
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-Thread");

        producerThread.start();
        consumerThread.start();
    }

    private void stopVi() {
        running = false;

        viChangeSaver.stop(); // 락 풀고 종료 신호 보냄

        if (producerThread != null) producerThread.interrupt();
        if (consumerThread != null) consumerThread.interrupt();

        viButton.setText("VI 시작");
        if (!viewStocks.isEmpty()) {
            statusLabel.setText(textMaker.makeStoppedText());
        }
    }

    private double[] getCurrentPrices() {
        double[] prices = new double[5];
        for (int i = 0; i < viewStocks.size(); i++) {
            prices[i] = viewStocks.get(i).currentPrice;
        }
        return prices;
    }

    private void applyChangesToViewStocks(double[] changes) {
        for (int i = 0; i < viewStocks.size(); i++) {
            viewStocks.get(i).applyChange(changes[i]);
        }
    }

    private void refreshRows() {
        for (int i = 0; i < viewStocks.size(); i++) {
            ViewStock stock = viewStocks.get(i);
            rowPanels.get(i).updateRow(i + 1, stock.stockName, stock.currentPrice, stock.getChangeRate());
        }
    }

    private static class ViewStock {
        private final String stockName;
        private final double basePrice;
        private double currentPrice;

        private ViewStock(String stockName, double basePrice) {
            this.stockName = stockName;
            this.basePrice = basePrice;
            this.currentPrice = basePrice;
        }

        private void applyChange(double change) {
            currentPrice += change;
            if (currentPrice < 1) {
                currentPrice = 1;
            }
        }

        private double getChangeRate() {
            return ((currentPrice - basePrice) / basePrice) * 100.0;
        }
    }
}