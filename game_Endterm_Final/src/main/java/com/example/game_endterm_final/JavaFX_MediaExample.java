package com.example.game_endterm_final;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class JavaFX_MediaExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Путь к видеофайлу
        String videoPath = "/Users/valtzmanmagnus/Desktop/GameProject_Java_Final/Miras_stradaet/game_Endterm_Final/src/design/Breath-of-the-Wild-The-Legend-of-Zelda.mp4";
        // Путь к аудиофайлу
        String musicPath = "/Users/valtzmanmagnus/Desktop/GameProject_Java_Final/Miras_stradaet/game_Endterm_Final/src/design/Zelda.mp3";

        // for me JANE
        // String videoPath = "/Users/valtzmanmagnus/Desktop/GameProject_Java_Final/Miras_stradaet/game_Endterm_Final/src/design/Breath-of-the-Wild-The-Legend-of-Zelda.mp4";
        // String musicPath = "/Users/valtzmanmagnus/Desktop/GameProject_Java_Final/Miras_stradaet/game_Endterm_Final/src/design/Zelda.mp3";

        // Проверяем, существуют ли файлы
        if (!Files.exists(Paths.get(videoPath)) || !Files.exists(Paths.get(musicPath))) {
            System.out.println("Файл не найден: " + videoPath + " или " + musicPath);
            return;
        }

        // Создаем медиа объекты
        Media videoMedia = new Media(new File(videoPath).toURI().toString());
        Media musicMedia = new Media(new File(musicPath).toURI().toString());

        // Создаем MediaPlayer объекты
        MediaPlayer videoPlayer = new MediaPlayer(videoMedia);
        MediaPlayer musicPlayer = new MediaPlayer(musicMedia);

        // Создаем MediaView для видео
        MediaView mediaView = new MediaView(videoPlayer);

        // Настройка воспроизведения видео
        videoPlayer.setAutoPlay(true);
        Timeline videoTimeline = new Timeline(new KeyFrame(Duration.seconds(3.9), event -> {
            videoPlayer.seek(Duration.ZERO);
            videoPlayer.play();
        }));
        videoTimeline.setCycleCount(Timeline.INDEFINITE);
        videoTimeline.play();

        // Настройка воспроизведения музыки
        musicPlayer.setAutoPlay(true);
        musicPlayer.setVolume(0.5); // Уменьшаем громкость музыки
        Timeline musicTimeline = new Timeline(new KeyFrame(Duration.minutes(2.7), event -> {
            musicPlayer.seek(Duration.ZERO);
            musicPlayer.play();
        }));
        musicTimeline.setCycleCount(Timeline.INDEFINITE);
        musicTimeline.play();

        // Настройка сцены и добавление MediaView в сцену
        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root, 1980, 1080);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing video and music");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
