package com.example.f23comp1011lhtodotasks;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartViewController implements Initializable {

    @FXML
    private LineChart<String, Integer> lineChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Integer> createdSeries = new XYChart.Series<>();

        //in the DBUtility class, you can loop over the result set and for each month create an
        //a new XYChart.Data<>() object
        createdSeries.getData().add(new XYChart.Data<>("August", 15));
        createdSeries.getData().add(new XYChart.Data<>("September", 50));
        createdSeries.getData().add(new XYChart.Data<>("October", 30));
        createdSeries.setName("Created");

        XYChart.Series<String, Integer> inProgress = new XYChart.Series<>();
        inProgress.getData().add(new XYChart.Data<>("August", 25));
        inProgress.getData().add(new XYChart.Data<>("September", 90));
        inProgress.getData().add(new XYChart.Data<>("October", 30));
        inProgress.setName("In Progress");
        lineChart.getData().addAll(createdSeries);
        lineChart.getData().addAll(inProgress);

    }
}
