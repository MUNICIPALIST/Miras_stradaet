package com.example.game_endterm_final;

import java.io.*;
import java.net.*;
import java.util.*;

public class TicTacToeServer {
    private ServerSocket serverSocket;
    private List<GameSession> sessions;

    public TicTacToeServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        sessions = new ArrayList<>();
    }

    public void start() {
        System.out.println("Tic Tac Toe Server is Running");
        try {
            while (true) {
                Socket player1 = serverSocket.accept();
                System.out.println("Player 1 has connected");
                Socket player2 = serverSocket.accept();
                System.out.println("Player 2 has connected");

                GameSession session = new GameSession(player1, player2);
                sessions.add(session);
                new Thread(session).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static class GameSession implements Runnable {
        private Socket player1;
        private Socket player2;
        private DataInputStream input1;
        private DataOutputStream output1;
        private DataInputStream input2;
        private DataOutputStream output2;

        public GameSession(Socket p1, Socket p2) throws IOException {
            player1 = p1;
            player2 = p2;
            input1 = new DataInputStream(player1.getInputStream());
            output1 = new DataOutputStream(player1.getOutputStream());
            input2 = new DataInputStream(player2.getInputStream());
            output2 = new DataOutputStream(player2.getOutputStream());
        }

        public void run() {
            try {
                output1.writeInt(1); // Player 1 plays 'X'
                output2.writeInt(2); // Player 2 plays 'O'

                boolean player1Turn = true;
                while (true) {
                    if (player1Turn) {
                        int cell = input1.readInt();
                        output2.writeInt(cell);
                    } else {
                        int cell = input2.readInt();
                        output1.writeInt(cell);
                    }
                    player1Turn = !player1Turn;
                }
            } catch (IOException e) {
                System.out.println("Error in game session: " + e.getMessage());
            } finally {
                closeConnection();
            }
        }

        private void closeConnection() {
            try { if (input1 != null) input1.close(); } catch (IOException e) {}
            try { if (output1 != null) output1.close(); } catch (IOException e) {}
            try { if (input2 != null) input2.close(); } catch (IOException e) {}
            try { if (output2 != null) output2.close(); } catch (IOException e) {}
            try { if (player1 != null) player1.close(); } catch (IOException e) {}
            try { if (player2 != null) player2.close(); } catch (IOException e) {}
        }
    }

    public static void main(String[] args) {
        try {
            TicTacToeServer server = new TicTacToeServer(12345);
            server.start();
        } catch (IOException e) {
            System.out.println("Unable to start server: " + e.getMessage());
        }
    }
}
