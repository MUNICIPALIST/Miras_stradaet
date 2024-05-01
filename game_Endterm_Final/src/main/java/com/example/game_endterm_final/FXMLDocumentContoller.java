package com.example.game_endterm_final;



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


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public Connection connectDB(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
                Connection connect
                       = DriverManager.getConnection("jdbc:mysql://localhost:8889/users", "root", "root");
                return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void login(){



    }

    public void register(){

        alertMessage alert = new alertMessage();

       if(signup_email.getText().isEmpty()  ||  signup_username.getText() .isEmpty()
           ||  signup_password.getText().isEmpty()  ||  signup_cPassword.getText().isEmpty()
           ||  signup_selectQuestion.getSelectionModel().getSelectedItem()  ==  null
           ||  signup_answer.getText().isEmpty()) {
           alert.errorMessage("All fields are necessary to be filled");
       }else if(signup_password.getText()  ==  signup_cPassword.getText()) {
           alert.errorMessage("Password does not match");
       }else if (signup_password.getText().length() < 8) {
           alert.errorMessage("Invalid Password, at least 8 characters needed");
       }else{

           String checkUsername = "SELECT * FROM users WHERE username = '"
                   + signup_username.getText() + "'";
           connect = connectDB();

           try {
               statement = connect.createStatement();
               result = statement.executeQuery(checkUsername);

               if(result.next()){
                   alert.errorMessage(signup_username.getText() + " is already taken");
               }else {
                   String insertData = "INSERT INTO users "
                           + "(email, username, password, question, answer) "
                           + "VALUES(?,?,?,?,?)";

                   prepare = connect.prepareStatement(insertData);
                   prepare.setString(1, signup_email.getText());
                   prepare.setString(2, signup_username.getText());
                   prepare.setString(3, signup_password.getText());
                   prepare.setString(4, (String) signup_selectQuestion.getSelectionModel() .getSelectedItem());
                   prepare.setString(5, signup_answer.getText());

                   prepare.executeUpdate();

                   alert.successMessage("Registered Successfully!");
               }
           }catch (Exception e){e.printStackTrace();}
       }
    }

    @Override
    public void initialize(URL url, ResourceBundle rd){

    }


}

