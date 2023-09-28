package com.example.f23comp1011lhtodotasks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTaskController implements Initializable {

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Label msgLabel;

    @FXML
    private Spinner<Integer> prioritySpinner;

    @FXML
    private TextField titleTextField;

    @FXML
    private ComboBox<Person> userComboBox;

    @FXML
    void submitTask(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configure the user combobox to receive a list of users from the database
        userComboBox.getItems().addAll(DBUtility.getUsers());
    }
}

