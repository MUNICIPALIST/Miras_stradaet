package com.example.game_endterm_final;

import javafx.scene.control.Button;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public void play(GameBoard gameBoard, Button button) {
        button.setText(getSymbol());
        button.setDisable(true);
    }
}


