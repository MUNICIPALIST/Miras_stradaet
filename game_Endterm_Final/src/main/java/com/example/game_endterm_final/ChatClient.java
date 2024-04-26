package com.example.game_endterm_final;

import java.io.*;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

// Клиент для чата и соединения с сервером
public class ChatClient {

    private final String serverAddress;
    private final int serverPort;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private TextArea chatArea; // Поле чата

    public ChatClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect(TextArea chatArea) throws IOException {
        this.chatArea = chatArea;
        socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) { // Чтение сообщений от сервера
                    String finalServerMessage = serverMessage;
                    Platform.runLater(() -> chatArea.appendText(finalServerMessage + "\n")); // Обновление поля чата
                }
            } catch (IOException e) {
                System.out.println("Error receiving message: " + e.getMessage());
            } finally {
                closeResources();
            }
        }).start(); // Запуск потока для обработки сообщений сервера
    }

    public void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush(); // Отправка сообщения
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    public void disconnect() {
        closeResources();
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
