package com.example.game_endterm_final;

import java.io.Serializable;

// Класс для хранения статистики игроков
public class PlayerStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private int totalWins;  // Общее количество побед
    private int totalGames; // Общее количество игр
    private int bestScore;  // Лучший результат

    public PlayerStats() {
        this.totalWins = 0;
        this.totalGames = 0;
        this.bestScore = 0;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void incrementTotalWins() {
        this.totalWins++;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void incrementTotalGames() {
        this.totalGames++;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void updateBestScore(int newScore) {
        if (newScore > bestScore) {
            bestScore = newScore;
        }
    }
}
