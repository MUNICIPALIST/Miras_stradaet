package com.example.game_endterm_final;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFX_MediaExample extends Application {

    private List<String> videoPaths = new ArrayList<>();
    private List<String> musicPaths = new ArrayList<>();
    private int currentVideoIndex = 0;
    private int currentMusicIndex = 0;
    private MediaPlayer videoPlayer;
    private MediaPlayer musicPlayer;
    private MediaView mediaView;
    private FadeTransition fadeTransition;

    @Override
    public void start(Stage primaryStage) {
        // Заполняем списки путей
        videoPaths.add("C:\\Users\\Нурислам\\Desktop\\Miras_stradaet\\game_Endterm_Final\\src\\design\\Pokemon-Emerald-Waterfall.mp4");
        videoPaths.add("C:\\Users\\Нурислам\\Desktop\\Miras_stradaet\\game_Endterm_Final\\src\\design\\Pokemon-Pinwheel-Forest.mp4");
        videoPaths.add("C:\\Users\\Нурислам\\Desktop\\Miras_stradaet\\game_Endterm_Final\\src\\design\\Breath-of-the-Wild-The-Legend-of-Zelda.mp4");
        videoPaths.add("C:\\Users\\Нурислам\\Desktop\\Miras_stradaet\\game_Endterm_Final\\src\\design\\Pokemon-Pinwheel-Forest.mp4");
        musicPaths.add("C:\\Users\\Нурислам\\Desktop\\Miras_stradaet\\game_Endterm_Final\\src\\design\\Theishter - Anime on Piano — Hai to Gensou no Grimgar OP - Knew Day (www.lightaudio.ru).mp3");
        musicPaths.add("C:\\Users\\Нурислам\\Desktop\\Miras_stradaet\\game_Endterm_Final\\src\\design\\Zelda.mp3");

        // Создаем MediaView для отображения видео
        mediaView = new MediaView();
        mediaView.setOpacity(0); // Изначально делаем MediaView невидимым

        // Настройка сцены
        Group root = new Group(mediaView);
        Scene scene = new Scene(root, 1980, 1080);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Media Player");
        primaryStage.show();

        // Инициализация и запуск первого видео и музыки
        loadAndPlayVideo(primaryStage, 0);
        loadAndPlayMusic(0);
    }

    private void loadAndPlayVideo(Stage stage, int index) {
        if (index >= videoPaths.size()) return; // Выход, если индекс вне диапазона
        File videoFile = new File(videoPaths.get(index));
        if (videoFile.exists()) {
            Media media = new Media(videoFile.toURI().toString());
            if (videoPlayer != null) videoPlayer.stop();
            videoPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(videoPlayer);

            // Настройка плавного перехода
            setupFadeTransition();

            videoPlayer.setAutoPlay(true);
            videoPlayer.setOnEndOfMedia(() -> loadAndPlayVideo(stage, ++currentVideoIndex % videoPaths.size()));
        }
    }

    private void setupFadeTransition() {
        if (fadeTransition != null) fadeTransition.stop();
        fadeTransition = new FadeTransition(Duration.seconds(1), mediaView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void loadAndPlayMusic(int index) {
        if (index >= musicPaths.size()) return; // Выход, если индекс вне диапазона
        File musicFile = new File(musicPaths.get(index));
        if (musicFile.exists()) {
            Media media = new Media(musicFile.toURI().toString());
            if (musicPlayer != null) musicPlayer.stop();
            musicPlayer = new MediaPlayer(media);
            musicPlayer.setAutoPlay(true);
            musicPlayer.setVolume(0.5);
            musicPlayer.setOnEndOfMedia(() -> loadAndPlayMusic(++currentMusicIndex % musicPaths.size()));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
