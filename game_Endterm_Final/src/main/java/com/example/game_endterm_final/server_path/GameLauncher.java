package com.example.game_endterm_final.server_path;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        // No need to use this primary stage, can be hidden or closed
        primaryStage.hide();

        // Launch Server in its own stage
        Stage serverStage = new Stage();
        TicTacToeServer serverApp = new TicTacToeServer();
        serverApp.start(serverStage);

        // Launch First Client in its own stage
        Stage client1Stage = new Stage();
        TicTacToeClient client1App = new TicTacToeClient();
        client1App.start(client1Stage);

        // Launch Second Client in its own stage
        Stage client2Stage = new Stage();
        TicTacToeClient2 client2App = new TicTacToeClient2();
        client2App.start(client2Stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
