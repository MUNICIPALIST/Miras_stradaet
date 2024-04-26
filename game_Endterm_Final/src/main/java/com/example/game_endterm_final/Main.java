package com.example.game_endterm_final;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.charset.CharsetEncoder;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public abstract class Main implements Initializable {

    @FXML
    private Button button_send;
    @FXML
    private TextField tf_message;
    @FXML
    private VBox vbox_message;
    @FXML
    private ScrollPane sp_main;

    private Server server;

    @Override
    public void initialize(URL location, ResourceBundle resources ) {

        try {
            server new Server(new ServerSocket(1234))
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating server.");
        }

        vbox_message.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        server.receiveMessageFromClient(vbox_message);

        button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String messageToSend = tf_message.getText();
                if (!messageToSend.isEmpty()) {
                    HBox hBox = new HBox();

                }
            }
        }


    }








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
