package com.example.game_endterm_final;

import java.io.*;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

// Клиент для игры в крестики-нолики с чатом
public class TicTacToeClient {

    private final String serverAddress;
    private final int serverPort;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private TextArea chatArea; // Поле для чата

    public TicTacToeClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = 12345; // Порт сервера
    }

    public void connect(TextArea chatArea) throws IOException {
        this.chatArea = chatArea;
        socket = new Socket(serverAddress, serverPort); // Подключение к серверу
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Вынесем обновление чата в отдельный метод, чтобы избежать ошибки с лямбда-выражением
        new Thread(() -> {
            try {
                while (true) {
                    String serverMessage = in.readLine(); // Получение сообщения
                    Platform.runLater(() -> updateChat(serverMessage)); // Вызов метода для обновления чата
                }
            } catch (IOException e) {
                System.out.println("Error receiving message: " + e.getMessage());
            } finally {
                closeResources(); // Закрытие ресурсов при завершении
            }
        }).start(); // Запуск потока для обработки входящих данных
    }

    private void updateChat(String message) {
        chatArea.appendText(message + "\n"); // Обновление чата
    }

    public void sendMessage(String message) {
        try {
            out.write(message + "\n"); // Отправка сообщения на сервер
            out.flush(); // Принудительное отправление
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    public void disconnect() {
        closeResources(); // Отключение от сервера
    }

    private void closeResources() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}