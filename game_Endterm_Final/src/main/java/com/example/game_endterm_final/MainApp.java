package com.example.game_endterm_final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Загрузка FXML файла
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginRegisterForgotPass.fxml"));
            Parent root = loader.load();

            // Настройка сцены
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("My Application");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX приложения
    }
}

