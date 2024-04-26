package com.example.game_endterm_final;


import javafx.scene.control.Button;

// Интерфейс для действий игрока или элементов игры
public interface Playable {
    // Метод для выполнения хода
    void play(GameBoard gameBoard, Button button);

    // Метод для определения имени
    String getName();

    // Метод для определения символа
    String getSymbol();
}
