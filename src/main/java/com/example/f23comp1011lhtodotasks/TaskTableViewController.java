package com.example.f23comp1011lhtodotasks;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaskTableViewController implements Initializable {

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
            tableView.getItems().clear();
            tableView.getItems().addAll(filteredTasks(newValue));
        });
    }

    /**
     * This method will return an ArrayList of tasks that contain the searchTerm
     * @param searchTerm
     * @return
     */
    private ArrayList<Task> filteredTasks(String searchTerm)
    {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task: allTasks)
        {
            if (task.contains(searchTerm))
                filteredTasks.add(task);
        }
        return filteredTasks;
    }
}
