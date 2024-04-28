package com.example.game_endterm_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Контроллер игры с чатом
public class Main implements Initializable {

    @FXML
    private TextArea chatArea; // Поле для чата
    @FXML
    private TextField chatInput; // Ввод для чата
    @FXML
    private Button sendButton; // Кнопка отправки

    private TicTacToeClient client; // Клиент для игры и чата

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new TicTacToeClient("localhost", 12345); // Подключение к серверу

        try {
            client.connect(chatArea); // Подключение к чату
        } catch (IOException e) {
            chatArea.appendText("Error connecting to server: " + e.getMessage());
        }

        sendButton.setOnAction(event -> sendMessage()); // Обработка нажатия кнопки отправки
    }

    private void sendMessage() {
        String message = chatInput.getText().trim(); // Получение текста из ввода
        if (!message.isEmpty()) {
            client.sendMessage("Player: " + message); // Отправка сообщения
            chatInput.clear(); // Очистка поля ввода
        }
    }

    @FXML
    void disconnectChat(ActionEvent event) {
        client.disconnect(); // Отключение от чата
    }

    @FXML
    void restartGame(ActionEvent event) {
        // Логика перезапуска игры
    }
}
