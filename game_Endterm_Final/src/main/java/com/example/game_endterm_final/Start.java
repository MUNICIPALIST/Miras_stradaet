package com.example.game_endterm_final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("tic_tac_toe.fxml"));
        primaryStage.setTitle("Our project");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}




// сдавать сервис, сделать pl ay marekt хоороший сервис комната матчи статистика профиль побед,
// как будет реализован функционал для 2-х более человек то-есть как будет подключаться к этому всему
// продовать не как игру а как сервис или же что то подобное
// 60 - 70 % максимум
// для 3 человек
// как фактический должно быть
// а что будет на самом деле мы все знаем как будет, будет ужасно разочеровывюще


