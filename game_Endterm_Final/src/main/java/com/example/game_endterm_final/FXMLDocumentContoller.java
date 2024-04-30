package com.example.game_endterm_final;
<<<<<<< HEAD:game_Endterm_Final/src/main/java/com/example/game_endterm_final/FXMLDocumentContoller.java
=======

>>>>>>> de9f4dfd5629fb4647f0e3d343faf58996ef231a:game_Endterm_Final/src/main/java/com/example/game_endterm_final/PleaseProvideControllerClassName.java
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class FXMLDocumentContoller implements Initializable{
    @FXML
    private Button changePass_backBtn;

    @FXML
    private PasswordField changePass_cPassword;

    @FXML
    private AnchorPane changePass_form;

    @FXML
    private PasswordField changePass_password;

    @FXML
    private Button changePass_proceedBtn;

    @FXML
    private TextField forgot_answer;

    @FXML
    private Button forgot_backBtn;

    @FXML
    private AnchorPane forgot_form;

    @FXML
    private Button forgot_proceedBtn;

    @FXML
    private ComboBox<?> forgot_selectQuestion;

    @FXML
    private TextField forgot_username;

    @FXML
    private Button login_btn;

    @FXML
    private Button login_createAccount;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private TextField login_showPassword;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField signup_answer;

    @FXML
    private Button signup_btn;

    @FXML
    private PasswordField signup_cPassword;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_loginAccount;

    @FXML
    private PasswordField signup_password;

    @FXML
    private ComboBox<?> signup_selectQuestion;

    @FXML
    private TextField signup_username;

<<<<<<< HEAD:game_Endterm_Final/src/main/java/com/example/game_endterm_final/FXMLDocumentContoller.java
    private Connection connection;
    private PreparedStatement prepar;
    private Result result;
    private Statement statement;

    public Connection connectDB(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
                Connection connect =
                        DriverManager.getConnection("");
                return null;
        }catch (Exception e) {e.printStackTrace();}
        return null;
    }

    public void login(){



    }

    public void register(){

       if(signup_email.getText().isEmpty()  ||  signup_username.getText() .isEmpty()
           ||  signup_password.getText().isEmpty()  ||  signup_cPassword.getText().isEmpty()
           ||  signup_selectQuestion.getSelectionModel().getSelectedItem()  ==  null
           ||  signup_answer.getText().isEmpty()) {

       }

    }


    @Override
    public void initialize(URL url, ResourceBundle rd){

    }

=======


>>>>>>> de9f4dfd5629fb4647f0e3d343faf58996ef231a:game_Endterm_Final/src/main/java/com/example/game_endterm_final/PleaseProvideControllerClassName.java
}

