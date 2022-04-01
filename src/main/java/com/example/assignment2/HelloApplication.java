package com.example.assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CSVReader reader = new CSVReader();
        ArrayList<ArrayList<String>> data;

        // Get the CSV data
        data = reader.ReadCSV();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        yAxis.setLabel("Number of Incidents");
        xAxis.setLabel("Incidents");

        BarChart barChart = new BarChart<>(xAxis,yAxis);

        barChart.setTitle("Fatal Incidents Between 85 to 99 vs Fatal Incidents Between 00 to 14 Amongst Airlines");
        barChart.setPrefWidth(1000);
        barChart.setPrefHeight(800);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Fatal Incidents Between 85 to 99");

        for (int i = 0; i < data.get(0).size(); i++) {
            series1.getData().add(new XYChart.Data(data.get(0).get(i), Integer.parseInt(data.get(3).get(i))));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Fatal Incidents Between 00 to 14");

        for (int i = 0; i < data.get(0).size(); i++) {
            series2.getData().add(new XYChart.Data(data.get(0).get(i), Integer.parseInt(data.get(6).get(i))));
        }

        barChart.getData().addAll(series1, series2);

        StackPane rootPane = new StackPane();

        Scene scene = new Scene(rootPane, 1000, 800);
        Pane pane = new Pane(barChart);
        rootPane.getChildren().addAll(pane);
        stage.setTitle("Part 3 of Assignment 2");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws IOException {
        AirlineData airline = new AirlineData();

        // Run function to create the XML files
        airline.assignment2();

        // Launch application for Part 3
        launch();
    }
}