package com.example.game_endterm_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

// Controller for Tic-Tac-Toe game without chat functionality
public class Main implements Initializable {

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @FXML
    private Text winnerText;

    private boolean isPlayerXTurn = true; // To keep track of who's turn it is

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};

        for (Button button : buttons) {
            button.setOnAction(event -> {
                if (button.getText().isEmpty()) { // Only allow to place mark if cell is empty
                    button.setText(isPlayerXTurn ? "X" : "O"); // Set text based on turn
                    isPlayerXTurn = !isPlayerXTurn; // Toggle turn
                    checkForWinner(); // Check if there's a winner
                }
            });
        }
    }

    private void checkForWinner() {
        String[][] grid = new String[][]{
                {button1.getText(), button2.getText(), button3.getText()},
                {button4.getText(), button5.getText(), button6.getText()},
                {button7.getText(), button8.getText(), button9.getText()}
        };

        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].isEmpty() && grid[i][0].equals(grid[i][1]) && grid[i][0].equals(grid[i][2])) {
                winnerText.setText(grid[i][0] + " wins!");
                disableButtons();
            }
            if (!grid[0][i].isEmpty() && grid[0][i].equals(grid[1][i]) && grid[0][i].equals(grid[2][i])) {
                winnerText.setText(grid[0][i] + " wins!");
                disableButtons();
            }
        }
        if (!grid[0][0].isEmpty() && grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2]) ||
                !grid[0][2].isEmpty() && grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])) {
            winnerText.setText(grid[1][1] + " wins!");
            disableButtons();
        }
    }

    private void disableButtons() {
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (Button button : buttons) {
            button.setDisable(true); // Disable all buttons after win
        }
    }

    @FXML
    void restartGame(ActionEvent event) {
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (Button button : buttons) {
            button.setText(""); // Clear text
            button.setDisable(false); // Enable button
        }
        isPlayerXTurn = true; // Reset to Player X's turn
        winnerText.setText(""); // Clear winner text
    }
}
