package com.example.game_endterm_final;

import javafx.scene.control.Button;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AIPlayer extends Player {

    private final ExecutorService executorService; // Служба потоков

    public AIPlayer(String name, String symbol) {
        super(name, symbol);
        this.executorService = Executors.newSingleThreadExecutor(); // Инициализация службы потоков
    }

    @Override
    public void play(GameBoard gameBoard, Button button) {
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2); // Имитируем время принятия решений
                button.setText(getSymbol());
                button.setDisable(true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Обработка прерывания
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}

