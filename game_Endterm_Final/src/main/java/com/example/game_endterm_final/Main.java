package com.example.game_endterm_final;

import java.net.URL;
import java.nio.charset.CharsetEncoder;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Main {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private char nowSym = 'x';

    private char gameField[][] = new char[3][3];

    private boolean isGame = true;

    @FXML
    void btnClick(ActionEvent event) {

        if(!isGame) return;

        Button btn = (Button)event.getSource();

        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);;

        gameField[rowIndex][columnIndex] = nowSym;

        btn.setText(String.valueOf(nowSym));

        if (gameField[0][0] == gameField[0][1] && gameField[0][0] == gameField[0][2] && (gameField[0][0] == 'x' || gameField[0][0] == '0'))  {
            isGame = false;
        }
        else if (gameField[1][0] == gameField[1][1] && gameField[1][0] == gameField[1][2] && (gameField[1][0] == 'x' || gameField[1][0] == '0'))  {
            isGame = false;
        }
        else if (gameField[2][0] == gameField[2][1] && gameField[2][0] == gameField[1][2] && (gameField[2][0] == 'x' || gameField[2][0] == '0'))  {
            isGame = false;
        }
        else if (gameField[0][0] == gameField[1][0] && gameField[0][0] == gameField[2][0] && (gameField[0][0] == 'x' || gameField[0][0] == '0'))  {
            isGame = false;
        }
        else if (gameField[0][1] == gameField[1][1] && gameField[0][1] == gameField[2][1] && (gameField[0][1] == 'x' || gameField[0][1] == '0'))  {
            isGame = false;
        }
        else if (gameField[0][2] == gameField[1][2] && gameField[0][2] == gameField[2][2] && (gameField[0][2] == 'x' || gameField[0][2] == '0'))  {
            isGame = false;
        }
        else if (gameField[0][0] == gameField[1][1] && gameField[0][0] == gameField[2][2] && (gameField[0][0] == 'x' || gameField[0][0] == '0'))  {
            isGame = false;
        }
        else if (gameField[2][2] == gameField[1][1] && gameField[2][2] == gameField[0][2] && (gameField[2][2] == 'x' || gameField[2][2] == '0'))  {
            isGame = false;
        }



        nowSym = nowSym == 'x' ? '0' : 'x';



    }

    @FXML
    void initialize() {

    }



}
