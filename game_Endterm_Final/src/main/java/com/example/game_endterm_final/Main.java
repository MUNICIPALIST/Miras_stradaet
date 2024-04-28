package com.example.game_endterm_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Контроллер игры в крестики-нолики с чатом
public class Main implements Initializable {

    @FXML
    private TextArea chatArea; // Поле для чата
    @FXML
    private TextField chatInput; // Ввод для чата
    @FXML
    private Button sendButton; // Кнопка отправки
    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @FXML
    private Text winnerText; // Текст для победителя

    private TicTacToeClient client; // Клиент для игры и чата

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new TicTacToeClient("localhost", 12345); // Подключение к серверу

        try {
            client.connect(chatArea); // Подключение к чату
        } catch (IOException e) {
            chatArea.appendText("Error connecting to server: " + e.getMessage());
        }

        sendButton.setOnAction(event -> sendMessage()); // Отправка сообщения

        setupButtonListeners(); // Установка обработчиков событий для кнопок
    }

    private void sendMessage() {
        String message = chatInput.getText().trim(); // Получение текста
        if (!message.isEmpty()) {
            client.sendMessage("Player: " + message); // Отправка в чат
            chatInput.clear(); // Очистка ввода
        }
    }

    private void setupButtonListeners() {
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnMouseClicked(event -> {
                if (buttons[index].getText().isEmpty()) { // Проверка пустой клетки
                    buttons[index].setText("X"); // Ход игрока
                    client.sendMessage("X" + index); // Отправка на сервер
                }
            });
        }
    }

    @FXML
    void restartGame(ActionEvent event) {
        // Логика перезапуска игры
    }

    @FXML
    void disconnectChat(ActionEvent event) {
        client.disconnect(); // Отключение от чата
    }
}
