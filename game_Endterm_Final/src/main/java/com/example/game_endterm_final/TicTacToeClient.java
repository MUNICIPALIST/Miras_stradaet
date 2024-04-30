package com.example.game_endterm_final;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class TicTacToeClient extends Application {
    private Button[][] buttons = new Button[3][3];
    private TextArea chatArea;
    private BufferedReader reader;
    private PrintWriter writer;
    private String playerSymbol;
    private boolean isPlayerTurn = false;
    private String serverAddress;
    private int serverPort;
    private Socket socket;

    public TicTacToeClient() {
        this("localhost", 8080); // Replace with default values that suit you
    }

    public TicTacToeClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setOnAction(e -> handleButtonClick(button));
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }

        chatArea = new TextArea();
        chatArea.setEditable(false);

        root.setCenter(gridPane);
        root.setBottom(chatArea);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            socket = new Socket(serverAddress, serverPort);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        if (message.startsWith("PLAYER")) {
                            playerSymbol = message.equals("PLAYER1") ? "X" : "O";
                            Platform.runLater(() -> chatArea.appendText("You are player " + playerSymbol + "\n"));
                            if (playerSymbol.equals("X")) {
                                isPlayerTurn = true;
                            }
                        } else {
                            int row = Integer.parseInt(message.substring(0, 1));
                            int col = Integer.parseInt(message.substring(1, 2));
                            String symbol = message.substring(2);
                            Platform.runLater(() -> buttons[row][col].setText(symbol));
                            if (symbol.equals(playerSymbol)) {
                                isPlayerTurn = false;
                            } else {
                                isPlayerTurn = true;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleButtonClick(Button button) {
        if (isPlayerTurn && button.getText().isEmpty()) {
            int row = GridPane.getRowIndex(button);
            int col = GridPane.getColumnIndex(button);
            button.setText(playerSymbol);
            writer.println(row + "" + col + playerSymbol);
            isPlayerTurn = false;
        }
    }

    public void connect(TextArea chatArea) throws IOException {
        this.chatArea = chatArea;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);

        new Thread(() -> {
            try {
                while (true) {
                    String serverMessage = reader.readLine();
                    Platform.runLater(() -> chatArea.appendText(serverMessage + "\n"));
                }
            } catch (IOException e) {
                System.out.println("Error receiving message: " + e.getMessage());
            } finally {
                closeResources();
            }
        }).start();
    }

    public void sendMessage(String message) {
        try {
            writer.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        closeResources();
    }

    private void closeResources() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}