package com.example.f23comp1011lhtodotasks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {

    @FXML
    private TextField confirmEmailTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label msgLabel;

    @FXML
    private TextField userNameTextField;

    @FXML
    void submit(ActionEvent event) throws SQLException {
        if (emailTextField.getText().equals(confirmEmailTextField.getText()))
        {
            try {
                Person person = new Person(userNameTextField.getText(), emailTextField.getText());
                msgLabel.setText(DBUtility.saveUserToDB(person));
            } catch (IllegalArgumentException e)
            {
                msgLabel.setText(e.getMessage());
            }
        }
        else
            msgLabel.setText("email and confirm email do not match");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLabel.setText("");
    }
}
