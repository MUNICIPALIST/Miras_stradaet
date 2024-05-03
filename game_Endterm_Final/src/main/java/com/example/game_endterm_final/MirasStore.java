package com.example.game_endterm_final;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class MirasStore {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        FontAwesomeIconView shipIcon = new FontAwesomeIconView();
        shipIcon.setGlyphName("SHIP");  // Указываем имя глифа
        shipIcon.setSize("50");  // Устанавливаем размер иконки
    }

}

