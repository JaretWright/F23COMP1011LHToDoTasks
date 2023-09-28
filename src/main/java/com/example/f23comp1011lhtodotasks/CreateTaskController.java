package com.example.f23comp1011lhtodotasks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateTaskController {

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Label msgLabel;

    @FXML
    private Spinner<?> prioritySpinner;

    @FXML
    private TextField titleTextField;

    @FXML
    private ComboBox<?> userComboBox;

    @FXML
    void submitTask(ActionEvent event) {

    }

}

