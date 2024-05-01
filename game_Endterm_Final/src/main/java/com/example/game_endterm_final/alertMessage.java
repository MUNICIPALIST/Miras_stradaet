package com.example.game_endterm_final;
import javafx.scene.control.Alert;
public class alertMessage {
    private Alert alert;

    public void errorMessage(String message){

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.getHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void successMessage(String message){

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.getHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
