package com.example.f23comp1011lhtodotasks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
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

        //configure the category combobox to receive a list of categories from the database
        categoryComboBox.getItems().addAll(DBUtility.getCategories());

        //add a default date of tomorrow
        dueDatePicker.setValue(LocalDate.now().plusDays(1));

        //configure the spinner to only accept the numbers 1, 2 or 3
        //i: = minimum
        //i1: = maximum
        //i2: = default value
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory
                                                                .IntegerSpinnerValueFactory(1,3,2);
        prioritySpinner.setValueFactory(spinnerValueFactory);
        Tooltip spinnerToolTip = new Tooltip("1=high priority\n2=medium priority\n3=low priority");
        prioritySpinner.setTooltip(spinnerToolTip);
    }
}

