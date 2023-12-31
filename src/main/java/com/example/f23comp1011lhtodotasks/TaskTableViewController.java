package com.example.f23comp1011lhtodotasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.OptionalDouble;
import java.util.ResourceBundle;

public class TaskTableViewController implements Initializable {

    @FXML
    private Label tasksShowingLabel;

    @FXML
    private Label avgOverDueLabel;

    @FXML
    private CheckBox priority1CheckBox;

    @FXML
    private CheckBox priority2CheckBox;

    @FXML
    private CheckBox priority3CheckBox;

    @FXML
    private TableColumn<Task, Person> assignedColumn;

    @FXML
    private TableColumn<Task, String> categoryColumn;

    @FXML
    private TableColumn<Task, Long> daysUntilDueColumn;

    @FXML
    private TextField filterTextField;

    @FXML
    private TableColumn<Task, Integer> priorityColumn;

    @FXML
    private TableColumn<Task, Status> statusColumn;

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, Integer> taskIdColumn;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    void changeToChartsView(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "chart-view.fxml" );
    }

    private ArrayList<Task> allTasks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allTasks = DBUtility.getTasks();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        try (
            Formatter formatter = new Formatter("tasks.json");
            )
        {

            formatter.format(gson.toJson(allTasks));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



        //configure the comboBox to have the priority values defined in the DB
//        priorityComboBox.getItems().addAll(allTasks.stream()
//                                                .mapToInt(task -> task.getPriority())
//                                                .distinct()
//                                                .sorted()
//                                                .boxed().toList());

        //configure the checkbox to filter for the priorities
        priority1CheckBox.setSelected(true);
        priority2CheckBox.setSelected(true);
        priority3CheckBox.setSelected(true);

        priority1CheckBox.addEventHandler(ActionEvent.ACTION,
                event -> filteredTasks(filterTextField.getText()));

        priority2CheckBox.addEventHandler(ActionEvent.ACTION,
                event -> filteredTasks(filterTextField.getText()));

        priority3CheckBox.addEventHandler(ActionEvent.ACTION,
                event -> filteredTasks(filterTextField.getText()));

        //connects the table columns to the specific data for each task
        assignedColumn.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));//calls getAssignedTo()
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));//calls getCategory()
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskID"));//calls getTaskID()
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        daysUntilDueColumn.setCellValueFactory(new PropertyValueFactory<>("daysUntilDue"));//call getDaysUntilDue()
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));

        tableView.getItems().addAll(allTasks);

        //add a change listener to the filterTextField
//        FilterTextChangeListener tcl = new FilterTextChangeListener();
        //an anonymous inner class
        filterTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredTasks(newValue);
        });

        updateLabels();
    }

    /**
     * This method will return an ArrayList of tasks that contain the searchTerm
     * @param searchTerm
     * @return
     */
    private void filteredTasks(String searchTerm)
    {
        tableView.getItems().clear();
        tableView.getItems().addAll(allTasks.stream()
                                             .filter(task -> task.contains(searchTerm, priority1CheckBox.isSelected(),
                                                                                     priority2CheckBox.isSelected(),
                                                                                     priority3CheckBox.isSelected()))  //can have as many steps as you
                                             .toList());
        updateLabels();
    }

    private void updateLabels()
    {
        tasksShowingLabel.setText("Tasks Showing: "+tableView.getItems().size());
        OptionalDouble avgOverDue = tableView.getItems().stream()
                                                        .filter(task -> task.getStatus() != Status.DONE)
                                                        .filter(task -> task.getDaysUntilDue() < 0)
                                                        .mapToLong(task -> task.getDaysUntilDue())
                                                        .average();

        avgOverDueLabel.setText("Avg Overdue: "+String.format("%.1f",avgOverDue.isPresent()?avgOverDue.getAsDouble():0));
    }
}
