package com.example.game_endterm_final;

import java.io.*;
import java.net.*;
import java.util.*;

// Сервер для игры в крестики-нолики с чатом
public class TicTacToeServer {

    private final ServerSocket serverSocket;
    private final List<GameSession> gameSessions; // Список игровых сессий

    public TicTacToeServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        gameSessions = new ArrayList<>();
    }

    public void start() {
        while (true) {
            try {
                Socket player1 = serverSocket.accept(); // Первый клиент
                Socket player2 = serverSocket.accept(); // Второй клиент

                GameSession gameSession = new GameSession(player1, player2); // Игровая сессия
                gameSessions.add(gameSession);
                new Thread(gameSession).start(); // Запуск игровой сессии в отдельном потоке
            } catch (IOException e) {
                System.out.println("Error accepting client: " + e.getMessage());
            }
        }
    }

    // Класс для игровой сессии между двумя игроками с чатом
    private static class GameSession implements Runnable {
        private final Socket player1;
        private final Socket player2;
        private final BufferedReader in1;
        private final BufferedWriter out1;
        private final BufferedReader in2;
        private final BufferedWriter out2;

        public GameSession(Socket player1, Socket player2) throws IOException {
            this.player1 = player1;
            this.player2 = player2;
            in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            out1 = new BufferedWriter(new OutputStreamWriter(player1.getOutputStream()));
            in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            out2 = new BufferedWriter(new OutputStreamWriter(player2.getOutputStream()));
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String messageFromPlayer1 = in1.readLine(); // Чтение сообщения от первого игрока
                    out2.write(messageFromPlayer1 + "\n"); // Передача второму игроку
                    out2.flush();

                    String messageFromPlayer2 = in2.readLine(); // Чтение сообщения от второго игрока
                    out1.write(messageFromPlayer2 + "\n"); // Передача первому игроку
                    out1.flush();
                }
            } catch (IOException e) {
                System.out.println("Game session ended: " + e.getMessage());
            } finally {
                closeResources(); // Закрытие ресурсов при завершении сессии
            }
        }

        private void closeResources() {
            try {
                in1.close();
                out1.close();
                in2.close();
                out2.close();
                player1.close();
                player2.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            TicTacToeServer server = new TicTacToeServer(12345); // Запуск сервера на порту 12345
            server.start(); // Старт сервера
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}
