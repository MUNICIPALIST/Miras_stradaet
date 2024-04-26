package com.example.game_endterm_final;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Класс для работы со статистикой
public class PlayerStatsIO {

    public static void saveStats(List<Player> players, File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Player player : players) {
                out.writeObject(player.getStats()); // Сохранение статистики
            }
        }
    }

    public static List<PlayerStats> loadStats(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            List<PlayerStats> statsList = new ArrayList<>();
            while (in.available() > 0) {
                statsList.add((PlayerStats) in.readObject()); // Загрузка статистики
            }
            return statsList;
        }
    }
}
