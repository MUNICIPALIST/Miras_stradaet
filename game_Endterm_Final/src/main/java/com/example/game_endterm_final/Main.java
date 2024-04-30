package com.example.game_endterm_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.application.Platform;

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

    private boolean isPlayerX = false;

    private TicTacToeClient client; // Клиент для игры и чата

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new TicTacToeClient("localhost", 8888); // Подключение к серверу

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
        // Reset the game board
        for (Button button : new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9}) {
            button.setText(""); // Clear text
            button.setDisable(false); // Re-enable the button
        }

        // Inform the server about the game restart
        client.sendMessage("Game restart");

        // Clear any additional game state or data if needed
    }

    @FXML
    void disconnectChat(ActionEvent event) {
        client.disconnect(); // Отключение от чата
    }
}
