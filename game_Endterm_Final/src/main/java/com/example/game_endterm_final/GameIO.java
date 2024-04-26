package com.example.game_endterm_final;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;

// Методы для сохранения и загрузки состояния игры
public class GameIO {

    public static void saveGame(GameState gameState, File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(gameState);
        }
    }

    public static GameState loadGame(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (GameState) in.readObject();
        }
    }

    public static File chooseSaveFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Game Files", "*.game")
        );
        return fileChooser.showSaveDialog(stage);
    }

    public static File chooseLoadFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Game");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Game Files", "*.game")
        );
        return fileChooser.showOpenDialog(stage);
    }
}




