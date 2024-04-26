package com.example.game_endterm_final;

import javafx.scene.control.Button;

public abstract class Player implements Playable {
    private String name;
    private String symbol;
    private PlayerStats stats; // Статистика игрока

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.stats = new PlayerStats(); // Инициализация статистики
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public PlayerStats getStats() {
        return stats; // Возвращает статистику игрока
    }

    @Override
    public abstract void play(GameBoard gameBoard, Button button);
}
