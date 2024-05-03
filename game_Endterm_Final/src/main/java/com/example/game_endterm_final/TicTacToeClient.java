package com.example.game_endterm_final;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import javafx.application.Platform;


public class TicTacToeClient extends Application {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private final String serverAddress = "localhost";
    private final int serverPort = 12345;
    private String playerType;
    private Button[] buttons = new Button[9];

    @Override
    public void start(Stage primaryStage) {
        try {
            socket = new Socket(serverAddress, serverPort);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            int player = input.readInt(); // Read player type from server
            playerType = (player == 1) ? "X" : "O";

            GridPane grid = new GridPane();
            for (int i = 0; i < 9; i++) {
                buttons[i] = new Button("-");
                buttons[i].setMinSize(90, 90);
                int finalI = i;
                buttons[i].setOnAction(e -> {
                    try {
                        if (buttons[finalI].getText().equals("-")) {
                            buttons[finalI].setText(playerType);
                            output.writeInt(finalI);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                grid.add(buttons[i], i % 3, i / 3);
            }

            Scene scene = new Scene(grid, 300, 300);
            primaryStage.setTitle("Tic Tac Toe: Player " + playerType);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Start a thread to listen for moves from the server
            new Thread(() -> {
                try {
                    while (true) {
                        int move = input.readInt();
                        Platform.runLater(() -> buttons[move].setText(playerType.equals("X") ? "O" : "X"));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
