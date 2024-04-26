package com.example.game_endterm_final;

import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Коллекция для хранения кнопок и победных комбинаций
public class GameBoard {
    private List<Button> buttons;
    private List<int[]> winCombinations; // Список для хранения победных комбинаций

    public GameBoard(Button... buttons) {
        this.buttons = new ArrayList<>(Arrays.asList(buttons));
        this.winCombinations = Arrays.asList( // Инициализация победных комбинаций
                new int[]{0, 1, 2},
                new int[]{3, 4, 5},
                new int[]{6, 7, 8},
                new int[]{0, 4, 8},
                new int[]{2, 4, 6},
                new int[]{0, 3, 6},
                new int[]{1, 4, 7},
                new int[]{2, 5, 8}
        );
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void resetBoard() {
        buttons.forEach(button -> {
            button.setDisable(false);
            button.setText("");
        });
    }

    public boolean checkWin(String symbol) {
        for (int[] combination : winCombinations) { // Проверка комбинаций
            if (buttons.get(combination[0]).getText().equals(symbol) &&
                    buttons.get(combination[1]).getText().equals(symbol) &&
                    buttons.get(combination[2]).getText().equals(symbol)) {
                return true;
            }
        }
        return false;
    }
}
