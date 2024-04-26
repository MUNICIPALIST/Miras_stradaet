package com.example.game_endterm_final;

import java.io.Serializable;
import java.util.List;

// Класс для сериализации состояния игры
public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private String winner;
    private List<String> boardState;
    private int xWinCount;
    private int oWinCount;

    public GameState(String winner, List<String> boardState, int xWinCount, int oWinCount) {
        this.winner = winner;
        this.boardState = boardState;
        this.xWinCount = xWinCount;
        this.oWinCount = oWinCount;
    }

    public String getWinner() {
        return winner;
    }

    public List<String> getBoardState() {
        return boardState;
    }

    public int getXWinCount() {
        return xWinCount;
    }

    public int getOWinCount() {
        return oWinCount;
    }
}
