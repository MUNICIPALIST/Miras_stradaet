package com.example.game_endterm_final;

import java.io.*;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.control.TextArea;


public class TicTacToeClient {

    private final String serverAddress;
    private final int serverPort;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private TextArea chatArea; // Поле для чата

    public TicTacToeClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;






    }

    public void connect(TextArea chatArea) throws IOException {
        this.chatArea = chatArea;
        socket = new Socket(serverAddress, serverPort); // Подключение к серверу
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) { // Чтение сообщений от сервера
                    String finalServerMessage = serverMessage;
                    Platform.runLater(() -> chatArea.appendText(finalServerMessage + "\n")); // Обновление чата
                }
            } catch (IOException e) {
                System.out.println("Error receiving message: " + e.getMessage());
            } finally {
                closeResources(); // Закрытие ресурсов
            }
        }).start(); // Запуск потока для обработки входящих данных
    }

    public void sendMessage(String message) {
        try {
            out.write(message + "\n"); // Отправка сообщения на сервер
            out.flush();
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    public void disconnect() {
        closeResources(); // Отключение от сервера
    }

    private void closeResources() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}
