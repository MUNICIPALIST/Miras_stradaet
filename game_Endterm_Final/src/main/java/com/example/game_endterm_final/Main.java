package com.example.game_endterm_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import java.util.ResourceBundle;

public class Main implements Initializable {

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @FXML
    private Text winnerText;

    @FXML
    private Text xWinsCounter, oWinsCounter;

    private GameBoard gameBoard;
    private List<Player> players;
    private Player currentPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        players = new ArrayList<>();
        Player playerX = new HumanPlayer("Player X", "X");
        Player playerO = new AIPlayer("Player O", "O"); // AIPlayer вместо HumanPlayer
        players.add(playerX);
        players.add(playerO);

        currentPlayer = players.get(0);

        gameBoard = new GameBoard(button1, button2, button3, button4, button5, button6, button7, button8, button9);

        gameBoard.getButtons().forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    @FXML
    void restartGame(ActionEvent event) {
        gameBoard.resetBoard();
        winnerText.setText("Tic-Tac-Toe");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            currentPlayer.play(gameBoard, button); // Асинхронное выполнение для AIPlayer

            if (gameBoard.checkWin(currentPlayer.getSymbol())) {
                currentPlayer.getStats().incrementTotalWins(); // Обновление статистики
                currentPlayer.getStats().incrementTotalGames(); // Обновление общей статистики

                if (currentPlayer.getSymbol().equals("X")) {
                    xWinsCounter.setText(String.valueOf(players.get(0).getStats().getTotalWins()));
                } else {
                    oWinsCounter.setText(String.valueOf(players.get(1).getStats().getTotalWins()));
                }

                gameBoard.getButtons().forEach(b -> b.setDisable(true));
            } else {
                switchPlayer();
            }
        });
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == players.get(0)) ? players.get(1) : players.get(0);
    }
}
