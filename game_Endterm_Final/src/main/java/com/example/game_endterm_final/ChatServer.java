package com.example.game_endterm_final;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Сервер для чата и управления клиентами
public class ChatServer {

    private final ServerSocket serverSocket;
    private final List<ClientHandler> clients; // Список клиентов

    public ChatServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.clients = new ArrayList<>();
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept(); // Ожидание подключения клиента
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start(); // Запуск потока для обработки клиента
            } catch (IOException e) {
                System.out.println("Error accepting client: " + e.getMessage());
            }
        }
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message); // Отправка сообщения всем клиентам, кроме отправителя
            }
        }
    }

    public synchronized void removeClient(ClientHandler client) {
        clients.remove(client); // Удаление клиента
    }

    private static class ClientHandler implements Runnable {

        private final Socket socket;
        private final ChatServer server;
        private BufferedReader in;
        private BufferedWriter out;

        public ClientHandler(Socket socket, ChatServer server) {
            this.socket = socket;
            this.server = server;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String clientMessage;

                while ((clientMessage = in.readLine()) != null) { // Чтение сообщений от клиента
                    server.broadcastMessage(clientMessage, this); // Отправка сообщения другим клиентам
                }
            } catch (IOException e) {
                System.out.println("Client disconnected: " + e.getMessage());
            } finally {
                server.removeClient(this); // Удаление клиента при разъединении
                closeResources();
            }
        }

        public void sendMessage(String message) {
            try {
                out.write(message + "\n");
                out.flush(); // Отправка сообщения
            } catch (IOException e) {
                System.out.println("Error sending message: " + e.getMessage());
            }
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

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer(12345); // Запуск сервера на порту 12345
            server.start(); // Старт сервера
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}
