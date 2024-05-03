package com.example.game_endterm_final;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MirasStore {


    @FXML
    private Button Game1; // TicTacToe
    @FXML
    private Button Game2; // Snake3D
    @FXML
    private Button Game3;
    @FXML
    private Button sound;

    // Method to handle TicTacToe game launch
    @FXML
    void handleTicTacToeAction(ActionEvent event) {
        // Must be run on the JavaFX application thread
        Platform.runLater(() -> {
            try {
                new GameLauncher().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Method to handle Snake game launch
    @FXML
    void handleSnakeAction(ActionEvent event) {
        // Must be run on the JavaFX application thread
        Platform.runLater(() -> {
            try {
                new Snake3dApp().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Method to handle ThiredGame game launch
    @FXML
    void handleArcadeAction(ActionEvent event) {
        // Must be run on the JavaFX application thread
        Platform.runLater(() -> {
            try {
                new com.example.game_endterm_final.thirdGame.ThiredGame().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


